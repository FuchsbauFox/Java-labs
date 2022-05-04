package ru.itmo.bank.offer.behavior;

import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.account.CreditAccount;
import ru.itmo.bank.account.state.credit.CreditDoubtful;
import ru.itmo.bank.account.state.credit.CreditStandard;
import ru.itmo.bank.offer.CreditOffer;

public class CreditBehavior implements Behavior {

  @Override
  public Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit) {
    CreditAccount account = new CreditAccount((CreditOffer) offer, id);

    if (isDoubtful) {
      account.transitionTo(new CreditDoubtful());
    } else {
      account.transitionTo(new CreditStandard());
    }

    return account;
  }

  @Override
  public String getNotifications() {
    return "There is a new credit offer";
  }
}
