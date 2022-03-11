package ru.itmo.bank.offers.behaviors;

import java.util.Calendar;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.DepositAccount;
import ru.itmo.bank.accounts.states.DepositStates.DepositDoubtful;
import ru.itmo.bank.accounts.states.DepositStates.DepositStandard;
import ru.itmo.bank.offers.DepositOffer;
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
