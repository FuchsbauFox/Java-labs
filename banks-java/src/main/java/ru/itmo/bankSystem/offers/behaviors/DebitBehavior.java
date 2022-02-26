package ru.itmo.bankSystem.offers.behaviors;

import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.accounts.DebitAccount;
import ru.itmo.bankSystem.accounts.states.DebitStates.DebitDoubtful;
import ru.itmo.bankSystem.accounts.states.DebitStates.DebitStandard;
import ru.itmo.bankSystem.offers.DebitOffer;

public class DebitBehavior implements Behavior {

  public Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit) {
    DebitAccount account = new DebitAccount((DebitOffer) offer, id);

    if (isDoubtful) {
      account.TransitionTo(new DebitDoubtful());
    } else {
      account.TransitionTo(new DebitStandard());
    }

    return account;
  }

  public String getNotifications() {
    return "There is a new debit offer";
  }
}
