package ru.itmo.bank.accounts.states.CreditStates;

import ru.itmo.bank.accounts.states.CreditState;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

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
