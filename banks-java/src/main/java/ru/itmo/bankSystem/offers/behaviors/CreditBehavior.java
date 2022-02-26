package ru.itmo.bankSystem.offers.behaviors;

import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.accounts.CreditAccount;
import ru.itmo.bankSystem.accounts.states.CreditStates.CreditDoubtful;
import ru.itmo.bankSystem.accounts.states.CreditStates.CreditStandard;
import ru.itmo.bankSystem.offers.CreditOffer;

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
