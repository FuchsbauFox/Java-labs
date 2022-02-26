package ru.itmo.bankSystem.accounts;

import java.util.ArrayList;
import java.util.List;
import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.offers.ItemInterest;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class Accrual {

  private Account account;
  private List<ItemInterest> interests;
  private int daysCounter;
  private float savings;

  public Accrual(Account account, List<ItemInterest> interests) {
    this.account = account;
    this.interests = new ArrayList<ItemInterest>(interests);
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
      account.replenishment(savings, "Accrual");
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
