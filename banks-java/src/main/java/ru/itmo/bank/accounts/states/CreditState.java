package ru.itmo.bank.accounts.states;

import ru.itmo.bank.accounts.CreditAccount;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public abstract class CreditState {

  protected CreditAccount account;

  public void SetContext(CreditAccount account) {
    this.account = account;
  }

  protected CreditAccount getAccount() {
    return account;
  }

  public abstract void checkWithdrawal(float money, boolean withoutCommission)
      throws TransactionCannotBeMade;

  public abstract void checkReplenishment(float money, boolean withoutCommission)
      throws TransactionCannotBeMade;

  public abstract float withdrawal(float money, boolean withoutCommission)
      throws TransactionCannotBeMade;

  public abstract float replenishment(float money, boolean withoutCommission)
      throws TransactionCannotBeMade;
}
