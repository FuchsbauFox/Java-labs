package ru.itmo.bank.accounts.states.deposit;

import ru.itmo.bank.accounts.states.DepositState;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DepositBlocked extends DepositState {

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }

  @Override
  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }

  @Override
  public float withdrawal(float money) throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }

  @Override
  public float replenishment(float money) throws TransactionCannotBeMade {
    throw new TransactionCannotBeMade();
  }
}
