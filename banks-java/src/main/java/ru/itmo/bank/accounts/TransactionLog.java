package ru.itmo.bank.accounts;

public class TransactionLog {

  private final int idTransaction;
  private LogTypes type;
  private final float money;
  private String idAccount;

  public TransactionLog(int idTransaction, LogTypes type, String idAccount, float money) {
    this.idTransaction = idTransaction;
    this.type = type;
    this.money = money;
    this.idAccount = idAccount;
  }

  public int getIdTransaction() {
    return idTransaction;
  }

  public String getType() {
    return type.toString() + idAccount;
  }

  public float getMoney() {
    return money;
  }

  public void cancelTransaction() {
    type = LogTypes.TransactionWasCancel;
  }
}
