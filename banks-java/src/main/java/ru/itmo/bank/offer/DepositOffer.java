package ru.itmo.bank.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.itmo.bank.Offer;
import ru.itmo.bank.offer.behavior.Behavior;
import ru.itmo.bank.offer.behavior.DepositBehavior;
import ru.itmo.tool.accountException.ArgumentNullException;
import ru.itmo.tool.accountException.FirstLimitDoesntStartAtZeroException;
import ru.itmo.tool.accountException.ListsDontFitTogetherException;
import ru.itmo.tool.accountException.ListsMustNotDecrease;
import ru.itmo.tool.accountException.NegativeOrNilInterestException;
import ru.itmo.tool.accountException.NegativeOrNilLimitDoubtfulAccountException;

public class DepositOffer implements Offer {

  private final List<ItemInterest> interests;
  private final float limitDoubtfulAccount;
  private final Behavior behavior;

  public DepositOffer(List<Float> from, List<Float> interest, float limitDoubtfulAccount)
      throws ListsMustNotDecrease, FirstLimitDoesntStartAtZeroException, ArgumentNullException, ListsDontFitTogetherException, NegativeOrNilInterestException, NegativeOrNilLimitDoubtfulAccountException {
    CheckListInterests(from, interest);
    CheckLimitDoubtfulAccount(limitDoubtfulAccount);

    interests = new ArrayList<>();
    for (int i = 0; i < from.size(); i++) {
      interests.add(new ItemInterest(from.get(i), interest.get(i)));
    }

    this.limitDoubtfulAccount = limitDoubtfulAccount;
    this.behavior = new DepositBehavior();
  }

  public DepositOffer(DepositOffer depositOffer) {
    this.interests = new ArrayList<>(depositOffer.getInterests());
    this.limitDoubtfulAccount = depositOffer.getLimitDoubtfulAccount();
    this.behavior = depositOffer.getBehavior();
  }

  public List<ItemInterest> getInterests() {
    return Collections.unmodifiableList(interests);
  }

  public float getLimitDoubtfulAccount() {
    return limitDoubtfulAccount;
  }

  @Override
  public Behavior getBehavior() {
    return behavior;
  }

  private void CheckListInterests(List<Float> from, List<Float> interest)
      throws NegativeOrNilInterestException, ListsMustNotDecrease, FirstLimitDoesntStartAtZeroException, ListsDontFitTogetherException, ArgumentNullException {
    if (from == null || interest == null) {
      throw new ArgumentNullException();
    }

    if (from.size() != interest.size()) {
      throw new ListsDontFitTogetherException();
    }

    if (from.get(0) != 0) {
      throw new FirstLimitDoesntStartAtZeroException();
    }

    if (interest.get(0) <= 0) {
      throw new NegativeOrNilInterestException();
    }

    for (int i = 1; i < from.size(); i++) {
      if (from.get(i - 1) >= from.get(i) || interest.get(i - 1) > interest.get(i)) {
        throw new ListsMustNotDecrease();
      }
    }
  }

  private void CheckLimitDoubtfulAccount(float limitDoubtfulAccount)
      throws NegativeOrNilLimitDoubtfulAccountException {
    if (limitDoubtfulAccount <= 0) {
      throw new NegativeOrNilLimitDoubtfulAccountException();
    }
  }
}
