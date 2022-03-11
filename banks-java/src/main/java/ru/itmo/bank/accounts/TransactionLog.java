package ru.itmo.bank.accounts;

public class TransactionLog {

  private int idTransaction;
  private String type;
  private float money;

  public TransactionLog(int idTransaction, String type, float money) {
    this.idTransaction = idTransaction;
    this.type = type;
    this.money = money;
  }

  public int getIdTransaction() {
    return idTransaction;
  }

  public String getType() {
    return type;
  }

  public float getMoney() {
    return money;
  }

  public void cancelTransaction() {
    type = "Transaction was cancel";
  }
}
