package ru.itmo.bankSystem;

import java.util.Calendar;
import java.util.List;
import ru.itmo.bankSystem.accounts.TransactionLog;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public interface Account {

  float getMoney();

  String getIdAccount();

  Calendar getOpenDate();

  List<TransactionLog> getTransactions();

  Offer getOffer();

  void checkWithdrawal(float money) throws TransactionCannotBeMade;

  void checkReplenishment(float money) throws TransactionCannotBeMade;

  void withdrawal(float money, String log) throws TransactionCannotBeMade;

  void replenishment(float money, String log) throws TransactionCannotBeMade;

  void accrualOfInterest() throws TransactionCannotBeMade;

  void block();

  void standard();
}
