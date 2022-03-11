package ru.itmo.bank.accounts.states;

import ru.itmo.bank.accounts.DepositAccount;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public abstract class DepositState {

  protected DepositAccount account;

  public void SetContext(DepositAccount account) {
    this.account = account;
  }

  protected DepositAccount getAccount() {
    return account;
  }

  public abstract void checkWithdrawal(float money) throws TransactionCannotBeMade;

  public abstract void checkReplenishment(float money) throws TransactionCannotBeMade;

  public abstract float withdrawal(float money) throws TransactionCannotBeMade;

  public abstract float replenishment(float money) throws TransactionCannotBeMade;

}
