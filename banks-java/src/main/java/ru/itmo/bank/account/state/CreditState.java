package ru.itmo.bank.account.state;

import ru.itmo.bank.account.CreditAccount;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

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
