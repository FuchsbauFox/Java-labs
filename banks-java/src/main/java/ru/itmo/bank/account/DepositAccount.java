package ru.itmo.bank.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.account.state.DepositState;
import ru.itmo.bank.account.state.deposit.DepositBlocked;
import ru.itmo.bank.account.state.deposit.DepositStandard;
import ru.itmo.bank.offer.DepositOffer;
import ru.itmo.tool.CalendarWeapon;
import ru.itmo.tool.accountException.IncorrectDateForDepositException;
import ru.itmo.tool.accountException.TransactionCannotBeMade;

public class DepositAccount implements Account {

  private final List<TransactionLog> transactions;
  private final Accrual accrual;
  private DepositState state;
  private int idLastTransaction;
  private float money;
  private final DepositOffer offer;
  private final String idAccount;
  private final Calendar depositEndDate;
  private final Calendar openDate;

  public DepositAccount(DepositOffer offer, String id, Calendar depositEndDate)
      throws IncorrectDateForDepositException {
    CalendarWeapon.getInstance().CheckDate(depositEndDate);

    this.transactions = new ArrayList<>();
    this.accrual = new Accrual(this, offer.getInterests());
    this.state = null;
    this.idLastTransaction = 0;
    this.money = 0;
    this.offer = offer;
    this.idAccount = id;
    this.depositEndDate = depositEndDate;
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

  @Override
  public List<TransactionLog> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  public Calendar getDepositEndDate() {
    return depositEndDate;
  }

  @Override
  public Calendar getOpenDate() {
    return openDate;
  }

  public void transitionTo(DepositState state) {
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
  public void withdrawal(float money, LOG_TYPES log, String idReceiverAccount) throws TransactionCannotBeMade {
    this.money -= state.withdrawal(money);

    transactions.add(new TransactionLog(idLastTransaction++, log, idReceiverAccount, money));
  }

  @Override
  public void replenishment(float money, LOG_TYPES log, String idSenderAccount) throws TransactionCannotBeMade {
    this.money += state.replenishment(money);
    transactions.add(new TransactionLog(idLastTransaction++, log, idSenderAccount, money));
  }

  @Override
  public void accrualOfInterest() throws TransactionCannotBeMade {
    accrual.NewDay();
  }

  @Override
  public void block() {
    transitionTo(new DepositBlocked());
  }

  @Override
  public void standard() {
    transitionTo(new DepositStandard());
  }
}
