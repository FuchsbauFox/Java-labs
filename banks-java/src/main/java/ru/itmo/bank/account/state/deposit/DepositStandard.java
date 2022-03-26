package ru.itmo.bank.account.state.deposit;

import ru.itmo.bank.account.state.DepositState;
import ru.itmo.tool.CalendarWeapon;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public class DepositStandard extends DepositState {

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    if (CalendarWeapon.getInstance()
        .CheckDate(account.getDepositEndDate(), CalendarWeapon.getInstance().getCalendar())
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
