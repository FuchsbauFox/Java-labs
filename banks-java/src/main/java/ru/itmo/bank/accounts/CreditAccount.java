package ru.itmo.bank.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.bank.accounts.states.CreditState;
import ru.itmo.bank.accounts.states.credit.CreditBlocked;
import ru.itmo.bank.accounts.states.credit.CreditStandard;
import ru.itmo.bank.offers.CreditOffer;
import ru.itmo.tools.CalendarWeapon;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;

public class CreditAccount implements Account {

  private final List<TransactionLog> transactions;
  private CreditState state;
  private int idLastTransaction;
  private float money;
  private final CreditOffer offer;
  private final String idAccount;
  private final Calendar openDate;

  public CreditAccount(CreditOffer offer, String id) {
    this.transactions = new ArrayList<>();
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

  @Override
  public List<TransactionLog> getTransactions() {
    return Collections.unmodifiableList(transactions);
  }

  @Override
  public Calendar getOpenDate() {
    return openDate;
  }

  public void transitionTo(CreditState state) {
    this.state = state;
    this.state.SetContext(this);
  }

  @Override
  public void checkWithdrawal(float money) throws TransactionCannotBeMade {
    state.checkWithdrawal(money, false);
  }

  @Override
  public void checkReplenishment(float money) throws TransactionCannotBeMade {
    state.checkReplenishment(money, false);
  }

  @Override
  public void withdrawal(float money, LogTypes log, String idReceiverAccount) throws TransactionCannotBeMade {
    if (this.money - money < 0 && log == LogTypes.CancelTransfer) {
      state.withdrawal(money - offer.getCommission(), true);
    } else {
      if (this.money - money < 0) {
        transactions.add(
            new TransactionLog(idLastTransaction, LogTypes.Commission, "", offer.getCommission()));
      }
      this.money -= state.withdrawal(money, false);
    }

    transactions.add(new TransactionLog(idLastTransaction++, log, idReceiverAccount, money));
  }

  @Override
  public void replenishment(float money, LogTypes log, String idSenderAccount) throws TransactionCannotBeMade {
    if (this.money < 0 && log == LogTypes.CancelTransfer) {
      state.replenishment(money + offer.getCommission(), true);
    } else {
      if (this.money < 0) {
        transactions.add(
            new TransactionLog(idLastTransaction, LogTypes.Commission, "", offer.getCommission()));
      }
      this.money += state.replenishment(money, false);
    }

    transactions.add(new TransactionLog(idLastTransaction++, log, idSenderAccount, money));
  }

  @Override
  public void accrualOfInterest() {
  }

  @Override
  public void block() {
    transitionTo(new CreditBlocked());
  }

  @Override
  public void standard() {
    transitionTo(new CreditStandard());
  }
}
