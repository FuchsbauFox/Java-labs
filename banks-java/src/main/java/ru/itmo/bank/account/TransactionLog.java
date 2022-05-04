package ru.itmo.bank.account;

public class TransactionLog {

  private final int idTransaction;
  private LOG_TYPES type;
  private final float money;
  private String idAccount;

  public TransactionLog(int idTransaction, LOG_TYPES type, String idAccount, float money) {
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
    type = LOG_TYPES.TransactionWasCancel;
  }
}
