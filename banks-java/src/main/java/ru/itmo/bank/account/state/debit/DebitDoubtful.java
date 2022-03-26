package ru.itmo.bank.account.state.debit;

import ru.itmo.bank.account.state.DebitState;
import ru.itmo.bank.offer.DebitOffer;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public class DebitDoubtful extends DebitState {

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    DebitOffer offer = (DebitOffer) account.getOffer();
    if (offer.getLimitDoubtfulAccount() < money || account.getMoney() - money < 0 || money < 0) {
      throw new TransactionCannotBeMade();
    }
  }

  @Override
  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    DebitOffer offer = (DebitOffer) account.getOffer();
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
