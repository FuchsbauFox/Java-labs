package ru.itmo.bank.accounts.states.DebitStates;

import ru.itmo.bank.accounts.states.DebitState;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DebitStandard extends DebitState {

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    if (account.getMoney() - money < 0 || money < 0) {
      throw new TransactionCannotBeMade();
    }
  }

  @Override
  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    if (money < 0) {
      throw new TransactionCannotBeMade();
    }
  }

  @Override
  public float withdrawal(float money) throws TransactionCannotBeMade {
    checkWithdrawal(money);
    return money;
  }

  @Override
  public float replenishment(float money) throws TransactionCannotBeMade {
    checkReplenishment(money);
    return money;
  }
}
