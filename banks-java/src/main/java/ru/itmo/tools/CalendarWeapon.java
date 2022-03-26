package ru.itmo.tools;

import java.util.Calendar;
import java.util.GregorianCalendar;
import ru.itmo.bank.CentralBank;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class CalendarWeapon {

  private static CalendarWeapon instance;
  private final Calendar calendar;

  private CalendarWeapon() {
    this.calendar = new GregorianCalendar();
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public static CalendarWeapon getInstance() {
    if (instance == null) {
      instance = new CalendarWeapon();
    }
    return instance;
  }

  public void skipDay() throws TransactionCannotBeMade {
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    makeAccrualOfInterest(1);
  }

  public void skipMonth() throws TransactionCannotBeMade {
    calendar.add(Calendar.MONTH, 1);
    makeAccrualOfInterest(30);
  }

  public void skipYear() throws TransactionCannotBeMade {
    calendar.add(Calendar.YEAR, 1);
    makeAccrualOfInterest(365);
  }

  public void CheckDate(Calendar date) throws IncorrectDateForDepositException {
    if (!CheckDate(calendar, date)) {
      throw new IncorrectDateForDepositException();
    }
  }

  public boolean CheckDate(Calendar currentDate, Calendar date) {
    return date.after(currentDate);
  }

  private void makeAccrualOfInterest(int days) throws TransactionCannotBeMade {
    for (var bank :
        CentralBank.getInstance().getBanks()) {
      for (var client :
          bank.getClients()) {
        for (var account :
            client.getAccounts()) {
          for (int i = 0; i < days; i++) {
            account.accrualOfInterest();
          }
        }
      }
    }
  }
}
