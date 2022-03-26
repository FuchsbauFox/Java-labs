package ru.itmo.bank.offers.behaviors;

import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.DebitAccount;
import ru.itmo.bank.accounts.states.debit.DebitDoubtful;
import ru.itmo.bank.accounts.states.debit.DebitStandard;
import ru.itmo.bank.offers.DebitOffer;

public class DebitBehavior implements Behavior {

  @Override
  public Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit) {
    DebitAccount account = new DebitAccount((DebitOffer) offer, id);

    if (isDoubtful) {
      account.transitionTo(new DebitDoubtful());
    } else {
      account.transitionTo(new DebitStandard());
    }

    return account;
  }

  @Override
  public String getNotifications() {
    return "There is a new debit offer";
  }
}
