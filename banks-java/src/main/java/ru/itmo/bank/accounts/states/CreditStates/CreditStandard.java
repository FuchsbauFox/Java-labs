package ru.itmo.bank.accounts.states.CreditStates;

import ru.itmo.bank.accounts.states.CreditState;
import ru.itmo.bank.offers.CreditOffer;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class CreditStandard extends CreditState {

  @Override
  public void checkWithdrawal(float money, boolean withoutCommission)
      throws TransactionCannotBeMade {
    CreditOffer offer = (CreditOffer) account.getOffer();
    if (account.getMoney() - money < 0 && !withoutCommission) {
      money += offer.getCommission();
    }

    if (offer.getLimit() < -1 * (account.getMoney() - money) || money < 0) {
      throw new TransactionCannotBeMade();
    }
  }

  @Override
  public void checkReplenishment(float money, boolean withoutCommission)
      throws TransactionCannotBeMade {
    CreditOffer offer = (CreditOffer) account.getOffer();
    if (account.getMoney() < 0 && !withoutCommission) {
      money -= offer.getCommission();
    }

    if (money < 0) {
      throw new TransactionCannotBeMade();
    }
  }

  @Override
  public float withdrawal(float money, boolean withoutCommission) throws TransactionCannotBeMade {
    CreditOffer offer = (CreditOffer) account.getOffer();
    checkWithdrawal(money, withoutCommission);
    if (account.getMoney() - money < 0 && !withoutCommission) {
      money += offer.getCommission();
    }
    return money;
  }

  @Override
  public float replenishment(float money, boolean withoutCommission)
      throws TransactionCannotBeMade {
    CreditOffer offer = (CreditOffer) account.getOffer();
    checkReplenishment(money, withoutCommission);
    if (account.getMoney() < 0 && !withoutCommission) {
      money -= offer.getCommission();
    }
    return money;
  }
}