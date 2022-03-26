package ru.itmo.bank.offer.behavior;

import java.util.Calendar;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.account.DepositAccount;
import ru.itmo.bank.account.state.deposit.DepositDoubtful;
import ru.itmo.bank.account.state.deposit.DepositStandard;
import ru.itmo.bank.offer.DepositOffer;
import ru.itmo.tool.CalendarWeapon;
import ru.itmo.tool.accountException.IncorrectDateForDepositException;

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
