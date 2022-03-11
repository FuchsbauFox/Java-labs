package ru.itmo.bank.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.states.CreditState;
import ru.itmo.bank.accounts.states.CreditStates.CreditBlocked;
import ru.itmo.bank.accounts.states.CreditStates.CreditStandard;
import ru.itmo.bank.offers.CreditOffer;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class CreditAccount implements Account {

  private List<TransactionLog> transactions;
  private CreditState state;
  private int idLastTransaction;
  private float money;
  private CreditOffer offer;
  private String idAccount;
  private Calendar openDate;

  public CreditAccount(CreditOffer offer, String id) {
    this.transactions = new ArrayList<TransactionLog>();
    this.state = null;
    this.idLastTransaction = 0;
    this.money = 0;
    this.offer = offer;
    this.idAccount = id;
    this.openDate = MyCalendar.getInstance().getCalendar();
  }

  public float getMoney() {
    return money;
  }

  public Offer getOffer() {
    return offer;
  }

  public String getIdAccount() {
    return idAccount;
  }

  public List<TransactionLog> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  public Calendar getOpenDate() {
    return openDate;
  }

  public void transitionTo(CreditState state) {
    this.state = state;
    this.state.SetContext(this);
  }

  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    state.checkWithdrawal(money, false);
  }

  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    state.checkReplenishment(money, false);
  }

  public void withdrawal(float money, String log) throws TransactionCannotBeMade {
    if (this.money - money < 0 && log.matches("CancelTransfer: [0-9]+")) {
      state.withdrawal(money - offer.getCommission(), true);
    } else {
      if (this.money - money < 0) {
        transactions.add(
            new TransactionLog(idLastTransaction, "Commission", offer.getCommission()));
      }
      this.money -= state.withdrawal(money, false);
    }

    transactions.add(new TransactionLog(idLastTransaction++, log, money));
  }

  public void replenishment(float money, String log) throws TransactionCannotBeMade {
    if (this.money < 0 && log.matches("CancelTransfer: [0-9]+")) {
      state.replenishment(money + offer.getCommission(), true);
    } else {
      if (this.money < 0) {
        transactions.add(
            new TransactionLog(idLastTransaction, "Commission", offer.getCommission()));
      }
      this.money += state.replenishment(money, false);
    }

    transactions.add(new TransactionLog(idLastTransaction++, log, money));
  }

  public void accrualOfInterest() {
  }

  public void block() {
    transitionTo(new CreditBlocked());
  }

  public void standard() {
    transitionTo(new CreditStandard());
  }
}
