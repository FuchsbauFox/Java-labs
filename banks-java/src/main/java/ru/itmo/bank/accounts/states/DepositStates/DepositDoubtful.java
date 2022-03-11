package ru.itmo.bank.accounts.states.DepositStates;

import ru.itmo.bank.accounts.states.DepositState;
import ru.itmo.bank.offers.DepositOffer;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DepositDoubtful extends DepositState {

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    DepositOffer offer = (DepositOffer) account.getOffer();
    if (offer.getLimitDoubtfulAccount() < money || MyCalendar.getInstance()
        .CheckDate(account.getDepositEndDate(), MyCalendar.getInstance().getCalendar()) ||
        money < 0) {
      throw new TransactionCannotBeMade();
    }
  }

  @Override
  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    DepositOffer offer = (DepositOffer) account.getOffer();
    if (offer.getLimitDoubtfulAccount() < money || money < 0) {
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
