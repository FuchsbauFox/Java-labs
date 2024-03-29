package ru.itmo.bank.offer;

import ru.itmo.bank.Offer;
import ru.itmo.bank.offer.behavior.Behavior;
import ru.itmo.bank.offer.behavior.CreditBehavior;
import ru.itmo.tool.accountException.CommissionMoreThanLimitDoubtfulAccountException;
import ru.itmo.tool.accountException.NegativeOrNilCommissionException;
import ru.itmo.tool.accountException.NegativeOrNilLimitDoubtfulAccountException;
import ru.itmo.tool.accountException.NegativeOrNilLimitException;

public class CreditOffer implements Offer {

  private final float commission;
  private final float limit;
  private final float limitDoubtfulAccount;
  private final Behavior behavior;

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

  @Override
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
