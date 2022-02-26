package ru.itmo.bankSystem.offers.behaviors;

import java.util.Calendar;
import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.accounts.DepositAccount;
import ru.itmo.bankSystem.accounts.states.DepositStates.DepositDoubtful;
import ru.itmo.bankSystem.accounts.states.DepositStates.DepositStandard;
import ru.itmo.bankSystem.offers.DepositOffer;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;

public class DepositBehavior implements Behavior {

  public Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit)
      throws IncorrectDateForDepositException {
    Calendar depositEndDate = MyCalendar.getInstance().getCalendar();
    depositEndDate.add(Calendar.MONTH, monthsForDeposit);

    var account = new DepositAccount((DepositOffer) offer, id, depositEndDate);

    if (isDoubtful) {
      account.TransitionTo(new DepositDoubtful());
    } else {
      account.TransitionTo(new DepositStandard());
    }

    return account;
  }

  public String getNotifications() {
    return "There is a new deposit offer";
  }
}
