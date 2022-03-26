package ru.itmo.bank;

import java.util.Calendar;
import java.util.List;
import ru.itmo.bank.accounts.LogTypes;
import ru.itmo.bank.accounts.TransactionLog;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public interface Account {

  float getMoney();

  String getIdAccount();

  Calendar getOpenDate();

  List<TransactionLog> getTransactions();

  Offer getOffer();

  void checkWithdrawal(float money) throws TransactionCannotBeMade;

  void checkReplenishment(float money) throws TransactionCannotBeMade;

  void withdrawal(float money, LogTypes log, String idReceiverAccount) throws TransactionCannotBeMade;

  void replenishment(float money, LogTypes log, String idSenderAccount) throws TransactionCannotBeMade;

  void accrualOfInterest() throws TransactionCannotBeMade;

  void block();

  void standard();
}
