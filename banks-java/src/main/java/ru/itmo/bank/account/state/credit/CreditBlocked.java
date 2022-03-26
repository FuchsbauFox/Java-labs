package ru.itmo.bank.account.state.credit;

import ru.itmo.bank.account.state.CreditState;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public class CreditBlocked extends CreditState {

  @Override
  public void checkWithdrawal(float money, boolean withoutCommission)
      throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }

  @Override
  public void checkReplenishment(float money, boolean withoutCommission)
      throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }

  @Override
  public float withdrawal(float money, boolean withoutCommission) throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }

  @Override
  public float replenishment(float money, boolean withoutCommission)
      throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }
}
