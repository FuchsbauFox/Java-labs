package ru.itmo.bank;

import java.util.Calendar;
import java.util.List;
import ru.itmo.bank.account.LOG_TYPES;
import ru.itmo.bank.account.TransactionLog;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public interface Account {

  float getMoney();

  String getIdAccount();

  Calendar getOpenDate();

  List<TransactionLog> getTransactions();

  Offer getOffer();

  void checkWithdrawal(float money) throws TransactionCannotBeMade;

  void checkReplenishment(float money) throws TransactionCannotBeMade;

  void withdrawal(float money, LOG_TYPES log, String idReceiverAccount) throws TransactionCannotBeMade;

  void replenishment(float money, LOG_TYPES log, String idSenderAccount) throws TransactionCannotBeMade;

  void accrualOfInterest() throws TransactionCannotBeMade;

  void block();

  void standard();
}
