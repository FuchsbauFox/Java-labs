package ru.itmo.bank.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.states.DebitState;
import ru.itmo.bank.accounts.states.DebitStates.DebitBlocked;
import ru.itmo.bank.accounts.states.DebitStates.DebitStandard;
import ru.itmo.bank.offers.DebitOffer;
import ru.itmo.bank.offers.ItemInterest;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DebitAccount implements Account {

  private List<TransactionLog> transactions;
  private Accrual accrual;
  private DebitState state;
  private int idLastTransaction;
  private float money;
  private DebitOffer offer;
  private String idAccount;
  private Calendar openDate;

  public DebitAccount(DebitOffer offer, String id) {
    this.transactions = new ArrayList<TransactionLog>();
    ArrayList<ItemInterest> item = new ArrayList<ItemInterest>();
    item.add(new ItemInterest(0, offer.getInterestOnBalance()));
    this.accrual = new Accrual(this, item);
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

  public void TransitionTo(DebitState state) {
    this.state = state;
    this.state.SetContext(this);
  }

  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    state.checkWithdrawal(money);
  }

  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    state.checkReplenishment(money);
  }

  public void withdrawal(float money, String log) throws TransactionCannotBeMade {
    this.money -= state.withdrawal(money);

    transactions.add(new TransactionLog(idLastTransaction++, log, money));
  }

  public void replenishment(float money, String log) throws TransactionCannotBeMade {
    this.money += state.replenishment(money);
    transactions.add(new TransactionLog(idLastTransaction++, log, money));
  }

  public void accrualOfInterest() throws TransactionCannotBeMade {
    accrual.NewDay();
  }

  public void block() {
    TransitionTo(new DebitBlocked());
  }

  public void standard() {
    TransitionTo(new DebitStandard());
  }
}
