package ru.itmo.bank.accounts.states.debit;

import ru.itmo.bank.accounts.states.DebitState;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DebitBlocked extends DebitState {

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
