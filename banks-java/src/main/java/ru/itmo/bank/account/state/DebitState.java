package ru.itmo.bank.account.state;

import ru.itmo.bank.account.DebitAccount;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public abstract class DebitState {

  protected DebitAccount account;

  public void SetContext(DebitAccount account) {
    this.account = account;
  }

  protected DebitAccount getAccount() {
    return account;
  }

  public abstract void checkWithdrawal(float money) throws TransactionCannotBeMade;

  public abstract void checkReplenishment(float money) throws TransactionCannotBeMade;

  public abstract float withdrawal(float money) throws TransactionCannotBeMade;

  public abstract float replenishment(float money) throws TransactionCannotBeMade;

}
