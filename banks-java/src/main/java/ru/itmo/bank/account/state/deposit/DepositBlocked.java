package ru.itmo.bank.account.state.deposit;

import ru.itmo.bank.account.state.DepositState;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

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
