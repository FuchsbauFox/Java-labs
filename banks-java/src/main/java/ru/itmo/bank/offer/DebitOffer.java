package ru.itmo.bank.offer;

import ru.itmo.bank.Offer;
import ru.itmo.bank.offer.behavior.Behavior;
import ru.itmo.bank.offer.behavior.DebitBehavior;
import ru.itmo.tool.accountException.NegativeOrNilInterestException;
import ru.itmo.tool.accountException.NegativeOrNilLimitDoubtfulAccountException;

public class DebitOffer implements Offer {

  private final float interestOnBalance;
  private final float limitDoubtfulAccount;
  private final Behavior behavior;

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

  @Override
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
