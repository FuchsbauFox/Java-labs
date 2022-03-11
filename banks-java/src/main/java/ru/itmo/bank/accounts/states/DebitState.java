package ru.itmo.bank.accounts.states;

import ru.itmo.bank.accounts.DebitAccount;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

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
