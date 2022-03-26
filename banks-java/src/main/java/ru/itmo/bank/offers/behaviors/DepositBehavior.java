package ru.itmo.bank.offers.behaviors;

import java.util.Calendar;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.DepositAccount;
import ru.itmo.bank.accounts.states.deposit.DepositDoubtful;
import ru.itmo.bank.accounts.states.deposit.DepositStandard;
import ru.itmo.bank.offers.DepositOffer;
import ru.itmo.tools.CalendarWeapon;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;

public class DepositBehavior implements Behavior {

  @Override
  public Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit)
      throws IncorrectDateForDepositException {
    Calendar depositEndDate = CalendarWeapon.getInstance().getCalendar();
    depositEndDate.add(Calendar.MONTH, monthsForDeposit);

    var account = new DepositAccount((DepositOffer) offer, id, depositEndDate);

    if (isDoubtful) {
      account.transitionTo(new DepositDoubtful());
    } else {
      account.transitionTo(new DepositStandard());
    }

    return account;
  }

  @Override
  public String getNotifications() {
    return "There is a new deposit offer";
  }
}
