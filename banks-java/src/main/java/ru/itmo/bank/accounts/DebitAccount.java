package ru.itmo.bank.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.states.DebitState;
import ru.itmo.bank.accounts.states.debit.DebitBlocked;
import ru.itmo.bank.accounts.states.debit.DebitStandard;
import ru.itmo.bank.offers.DebitOffer;
import ru.itmo.bank.offers.ItemInterest;
import ru.itmo.tools.CalendarWeapon;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DebitAccount implements Account {

  private final List<TransactionLog> transactions;
  private final Accrual accrual;
  private DebitState state;
  private int idLastTransaction;
  private float money;
  private final DebitOffer offer;
  private final String idAccount;
  private final Calendar openDate;

  public DebitAccount(DebitOffer offer, String id) {
    this.transactions = new ArrayList<>();
    ArrayList<ItemInterest> item = new ArrayList<>();
    item.add(new ItemInterest(0, offer.getInterestOnBalance()));
    this.accrual = new Accrual(this, item);
    this.state = null;
    this.idLastTransaction = 0;
    this.money = 0;
    this.offer = offer;
    this.idAccount = id;
    this.openDate = CalendarWeapon.getInstance().getCalendar();
  }

  @Override
  public float getMoney() {
    return money;
  }

  @Override
  public Offer getOffer() {
    return offer;
  }

  @Override
  public String getIdAccount() {
    return idAccount;
  }

  public List<TransactionLog> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  @Override
  public Calendar getOpenDate() {
    return openDate;
  }

  public void transitionTo(DebitState state) {
    this.state = state;
    this.state.SetContext(this);
  }

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    state.checkWithdrawal(money);
  }

  @Override
  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    state.checkReplenishment(money);
  }

  @Override
  public void withdrawal(float money, LogTypes log, String idReceiverAccount) throws TransactionCannotBeMade {
    this.money -= state.withdrawal(money);
    transactions.add(new TransactionLog(idLastTransaction++, log, idReceiverAccount, money));
  }

  @Override
  public void replenishment(float money, LogTypes log, String idSenderAccount) throws TransactionCannotBeMade {
    this.money += state.replenishment(money);
    transactions.add(new TransactionLog(idLastTransaction++, log, idSenderAccount, money));
  }

  @Override
  public void accrualOfInterest() throws TransactionCannotBeMade {
    accrual.NewDay();
  }

  @Override
  public void block() {
    transitionTo(new DebitBlocked());
  }

  @Override
  public void standard() {
    transitionTo(new DebitStandard());
  }
}
