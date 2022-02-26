package ru.itmo.bankSystem.offers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.offers.behaviors.Behavior;
import ru.itmo.bankSystem.offers.behaviors.DepositBehavior;
import ru.itmo.tools.accountExceptions.ArgumentNullException;
import ru.itmo.tools.accountExceptions.FirstLimitDoesntStartAtZeroException;
import ru.itmo.tools.accountExceptions.ListsDontFitTogetherException;
import ru.itmo.tools.accountExceptions.ListsMustNotDecrease;
import ru.itmo.tools.accountExceptions.NegativeOrNilInterestException;
import ru.itmo.tools.accountExceptions.NegativeOrNilLimitDoubtfulAccountException;

public class DepositOffer implements Offer {

  private List<ItemInterest> interests;
  private float limitDoubtfulAccount;
  private Behavior behavior;

  public DepositOffer(ArrayList<Float> from, List<Float> interest, float limitDoubtfulAccount)
      throws ListsMustNotDecrease, FirstLimitDoesntStartAtZeroException, ArgumentNullException, ListsDontFitTogetherException, NegativeOrNilInterestException, NegativeOrNilLimitDoubtfulAccountException {
    CheckListInterests(from, interest);
    CheckLimitDoubtfulAccount(limitDoubtfulAccount);

    interests = new ArrayList<ItemInterest>();
    for (int i = 0; i < from.size(); i++) {
      interests.add(new ItemInterest(from.get(i), interest.get(i)));
    }

    this.limitDoubtfulAccount = limitDoubtfulAccount;
    this.behavior = new DepositBehavior();
  }

  public DepositOffer(DepositOffer depositOffer) {
    this.interests = new ArrayList<ItemInterest>(depositOffer.getInterests());
    this.limitDoubtfulAccount = depositOffer.getLimitDoubtfulAccount();
    this.behavior = depositOffer.getBehavior();
  }

  public List<ItemInterest> getInterests() {
    return Collections.unmodifiableList(interests);
  }

  public float getLimitDoubtfulAccount() {
    return limitDoubtfulAccount;
  }

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
