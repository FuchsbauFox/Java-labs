package ru.itmo.bank.offers.behaviors;

import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.CreditAccount;
import ru.itmo.bank.accounts.states.CreditStates.CreditDoubtful;
import ru.itmo.bank.accounts.states.CreditStates.CreditStandard;
import ru.itmo.bank.offers.CreditOffer;

public class CreditBehavior implements Behavior {

  public Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit) {
    CreditAccount account = new CreditAccount((CreditOffer) offer, id);

    if (isDoubtful) {
      account.transitionTo(new CreditDoubtful());
    } else {
      account.transitionTo(new CreditStandard());
    }

    return account;
  }

  public String getNotifications() {
    return "There is a new credit offer";
  }
}
