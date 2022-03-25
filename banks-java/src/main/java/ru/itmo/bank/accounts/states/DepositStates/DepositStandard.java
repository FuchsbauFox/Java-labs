package ru.itmo.bank.accounts.states.DepositStates;

import ru.itmo.bank.accounts.states.DepositState;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DepositStandard extends DepositState {

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    if (MyCalendar.getInstance()
        .CheckDate(account.getDepositEndDate(), MyCalendar.getInstance().getCalendar())
        || money < 0) {
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