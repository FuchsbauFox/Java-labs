package ru.itmo.bank.account;

import java.util.ArrayList;
import java.util.List;
import ru.itmo.bank.Account;
import ru.itmo.bank.offer.ItemInterest;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public class Accrual {

  private final Account account;
  private final List<ItemInterest> interests;
  private int daysCounter;
  private float savings;

  public Accrual(Account account, List<ItemInterest> interests) {
    this.account = account;
    this.interests = new ArrayList<>(interests);
    this.daysCounter = 0;
    this.savings = 0;
  }

  public int getDaysCounter() {
    return daysCounter;
  }

  public float getSavings() {
    return savings;
  }

  public void NewDay() throws TransactionCannotBeMade {
    if (daysCounter == 30) {
      account.replenishment(savings, LOG_TYPES.Accrual, "");
      daysCounter = 0;
      savings = 0;
    }

    for (int i = interests.size() - 1; i >= 0; i--) {
      if (interests.get(i).getFrom() < account.getMoney()) {
        savings += account.getMoney() * interests.get(i).getInterest() / 365;
        daysCounter++;
        break;
      }
    }
  }
}
