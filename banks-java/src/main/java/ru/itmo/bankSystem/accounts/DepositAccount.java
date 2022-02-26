package ru.itmo.bankSystem.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.accounts.states.DepositState;
import ru.itmo.bankSystem.accounts.states.DepositStates.DepositBlocked;
import ru.itmo.bankSystem.accounts.states.DepositStates.DepositStandard;
import ru.itmo.bankSystem.offers.DepositOffer;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class DepositAccount implements Account {

  private List<TransactionLog> transactions;
  private Accrual accrual;
  private DepositState state;
  private int idLastTransaction;
  private float money;
  private DepositOffer offer;
  private String idAccount;
  private Calendar depositEndDate;
  private Calendar openDate;

  public DepositAccount(DepositOffer offer, String id, Calendar depositEndDate)
      throws IncorrectDateForDepositException {
    MyCalendar.getInstance().CheckDate(depositEndDate);

    this.transactions = new ArrayList<TransactionLog>();
    this.accrual = new Accrual(this, offer.getInterests());
    this.state = null;
    this.idLastTransaction = 0;
    this.money = 0;
    this.offer = offer;
    this.idAccount = id;
    this.depositEndDate = depositEndDate;
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

  public Calendar getDepositEndDate() {
    return depositEndDate;
  }

  public Calendar getOpenDate() {
    return openDate;
  }

  public void TransitionTo(DepositState state) {
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
    TransitionTo(new DepositBlocked());
  }

  public void standard() {
    TransitionTo(new DepositStandard());
  }
}
