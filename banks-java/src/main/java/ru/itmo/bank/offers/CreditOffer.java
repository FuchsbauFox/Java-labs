package ru.itmo.bank.offers;

import ru.itmo.bank.Offer;
import ru.itmo.bank.offers.behaviors.Behavior;
import ru.itmo.bank.offers.behaviors.CreditBehavior;
import ru.itmo.tools.accountExceptions.CommissionMoreThanLimitDoubtfulAccountException;
import ru.itmo.tools.accountExceptions.NegativeOrNilCommissionException;
import ru.itmo.tools.accountExceptions.NegativeOrNilLimitDoubtfulAccountException;
import ru.itmo.tools.accountExceptions.NegativeOrNilLimitException;

public class CreditOffer implements Offer {

  private float commission;
  private float limit;
  private float limitDoubtfulAccount;
  private Behavior behavior;

  public CreditOffer() {
  }

  public CreditOffer(float commission, float limit, float limitDoubtfulAccount)
      throws NegativeOrNilCommissionException, CommissionMoreThanLimitDoubtfulAccountException, NegativeOrNilLimitException, NegativeOrNilLimitDoubtfulAccountException {
    checkCommission(commission, limitDoubtfulAccount);
    checkLimit(limit);
    checkLimitDoubtfulAccount(limitDoubtfulAccount);

    this.commission = commission;
    this.limit = limit;
    this.limitDoubtfulAccount = limitDoubtfulAccount;
    this.behavior = new CreditBehavior();
  }

  public CreditOffer(CreditOffer creditOffer) {
    this.commission = creditOffer.getCommission();
    this.limit = creditOffer.getLimit();
    this.limitDoubtfulAccount = creditOffer.getLimitDoubtfulAccount();
    this.behavior = creditOffer.getBehavior();
  }

  public float getCommission() {
    return commission;
  }

  public float getLimit() {
    return limit;
  }

  public float getLimitDoubtfulAccount() {
    return limitDoubtfulAccount;
  }

  public Behavior getBehavior() {
    return behavior;
  }

  private void checkCommission(float commission, float limitDoubtfulAccount)
      throws CommissionMoreThanLimitDoubtfulAccountException, NegativeOrNilCommissionException {
    if (commission <= 0) {
      throw new NegativeOrNilCommissionException();
    }

    if (commission > limitDoubtfulAccount) {
      throw new CommissionMoreThanLimitDoubtfulAccountException();
    }
  }

  private void checkLimit(float limit) throws NegativeOrNilLimitException {
    if (limit <= 0) {
      throw new NegativeOrNilLimitException();
    }
  }

  private void checkLimitDoubtfulAccount(float limitDoubtfulAccount)
      throws NegativeOrNilLimitDoubtfulAccountException {
    if (limitDoubtfulAccount <= 0)
      throw new NegativeOrNilLimitDoubtfulAccountException();
  }
}
