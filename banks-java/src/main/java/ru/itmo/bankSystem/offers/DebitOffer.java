package ru.itmo.bankSystem.offers;

import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.offers.behaviors.Behavior;
import ru.itmo.bankSystem.offers.behaviors.DebitBehavior;
import ru.itmo.tools.accountExceptions.NegativeOrNilInterestException;
import ru.itmo.tools.accountExceptions.NegativeOrNilLimitDoubtfulAccountException;

public class DebitOffer implements Offer {

  private float interestOnBalance;
  private float limitDoubtfulAccount;
  private Behavior behavior;

  public DebitOffer(float interestOnBalance, float limitDoubtfulAccount)
      throws NegativeOrNilInterestException, NegativeOrNilLimitDoubtfulAccountException {
    checkInterest(interestOnBalance);
    checkLimitDoubtfulAccount(limitDoubtfulAccount);

    this.interestOnBalance = interestOnBalance;
    this.limitDoubtfulAccount = limitDoubtfulAccount;
    this.behavior = new DebitBehavior();
  }

  public DebitOffer(DebitOffer debitOffer) {
    this.interestOnBalance = debitOffer.getInterestOnBalance();
    this.limitDoubtfulAccount = debitOffer.getLimitDoubtfulAccount();
    this.behavior = debitOffer.getBehavior();
  }

  public float getInterestOnBalance() {
    return interestOnBalance;
  }

  public float getLimitDoubtfulAccount() {
    return limitDoubtfulAccount;
  }

  public Behavior getBehavior() {
    return behavior;
  }

  private void checkInterest(float limit) throws NegativeOrNilInterestException {
    if (limit <= 0)
      throw new NegativeOrNilInterestException();
  }

  private void checkLimitDoubtfulAccount(float limitDoubtfulAccount)
      throws NegativeOrNilLimitDoubtfulAccountException {
    if (limitDoubtfulAccount <= 0) {
      throw new NegativeOrNilLimitDoubtfulAccountException();
    }
  }
}
