package ru.itmo.bank.offers.behaviors;

import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.DebitAccount;
import ru.itmo.bank.accounts.states.DebitStates.DebitDoubtful;
import ru.itmo.bank.accounts.states.DebitStates.DebitStandard;
import ru.itmo.bank.offers.DebitOffer;

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
