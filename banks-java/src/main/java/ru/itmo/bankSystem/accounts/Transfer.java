package ru.itmo.bankSystem.accounts;

import java.util.Objects;
import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.CentralBank;
import ru.itmo.tools.accountExceptions.AccountNotFoundException;
import ru.itmo.tools.accountExceptions.ErrorCancelTransaction;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.tools.accountExceptions.TransactionNotFoundException;

public class Transfer {

  private static Transfer instance;

  private Transfer() {
  }

  public static Transfer getInstance() {
    if (instance == null) {
      instance = new Transfer();
    }
    return instance;
  }

  public void makeTransfer(String idSenderAccount, String idReceiverAccount, float money)
      throws TransactionCannotBeMade, AccountNotFoundException {
    Account senderAccount = getAccount(idSenderAccount);
    Account receiverAccount = getAccount(idReceiverAccount);

    senderAccount.checkWithdrawal(money);
    receiverAccount.checkReplenishment(money);
    senderAccount.withdrawal(money, "TransferTo: " + idReceiverAccount);
    receiverAccount.replenishment(money, "TransferFrom: " + idSenderAccount);
  }

  public void cancelTransfer(String idAccount, int idTransaction)
      throws TransactionNotFoundException, TransactionCannotBeMade, AccountNotFoundException, ErrorCancelTransaction {
    Account senderAccount = getAccount(idAccount);

    TransactionLog commissionLog = null;
    for (var transaction :
        senderAccount.getTransactions()) {
      if (transaction.getIdTransaction() == idTransaction && Objects.equals(transaction.getType(),
          "Commission")) {
        commissionLog = transaction;
        break;
      }
    }

    TransactionLog log = null;
    for (var transaction :
        senderAccount.getTransactions()) {
      if (transaction.getIdTransaction() == idTransaction &&
          transaction.getType().matches("^TransferTo: [0-9]\\d{8}")) {
        log = transaction;
        break;
      }
    }

    if (log == null) {
      throw new TransactionNotFoundException();
    }
    Account receiverAccount = getAccount(
        log.getType().substring(log.getType().indexOf(" ") + 1, log.getType().length() - 1));
    TransactionLog logReceiver = null;
    for (var transaction :
        receiverAccount.getTransactions()) {
      if (Math.abs(transaction.getMoney() - log.getMoney()) < 0.001 && Objects.equals(
          transaction.getType(), "TransferFrom: " + idAccount)) {
        logReceiver = transaction;
        break;
      }
    }

    if (logReceiver == null) {
      throw new ErrorCancelTransaction();
    }

    receiverAccount.checkWithdrawal(log.getMoney());
    senderAccount.checkReplenishment(log.getMoney());
    receiverAccount.withdrawal(log.getMoney(), "CancelTransfer: " + logReceiver.getIdTransaction());
    senderAccount.replenishment(log.getMoney(), "CancelTransfer: " + idTransaction);

    if (commissionLog != null) {
      commissionLog.cancelTransaction();
    }
    logReceiver.cancelTransaction();
    log.cancelTransaction();
  }

  private Account getAccount(String id) throws AccountNotFoundException {
    Account findAccount = null;
    for (var client :
        CentralBank.getInstance().getBanks()
            .get(Integer.parseInt(id.substring(0, 3))).getClients()) {
      for (var account :
          client.getAccounts()) {
        if (Objects.equals(account.getIdAccount(), id)) {
          findAccount = account;
          break;
        }
      }
    }

    if (findAccount == null) {
      throw new AccountNotFoundException();
    }

    return findAccount;
  }
}