package ru.itmo.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import ru.itmo.bank.accounts.LogTypes;
import ru.itmo.bank.accounts.Transfer;
import ru.itmo.tools.accountExceptions.AccountNotFoundException;
import ru.itmo.tools.accountExceptions.ErrorCancelTransaction;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.tools.accountExceptions.TransactionNotFoundException;
import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.tools.clientExceptions.InvalidAddressException;
import ru.itmo.tools.clientExceptions.InvalidClientNameException;
import ru.itmo.tools.clientExceptions.InvalidPassportException;

public class Client {

  private final List<Account> accounts;
  private String password;
  private List<String> notifications;
  private String fullName;
  private String passport;
  private String address;
  private String phoneNumber;

  public Client() {
    accounts = new ArrayList<>();
    password = null;
    notifications = new ArrayList<>();
    fullName = null;
    passport = null;
    address = null;
    phoneNumber = null;
  }

  public String getFullName() {
    return fullName;
  }

  public String getPassport() {
    return passport;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public List<String> getNotifications() {
    return Collections.unmodifiableList(notifications);
  }

  public List<Account> getAccounts() {
    return Collections.unmodifiableList(accounts);
  }

  public void withdrawal(String idMyAccount, float money)
      throws TransactionCannotBeMade, AccountNotFoundException {
    checkAccount(idMyAccount);
    for (Account account :
        accounts) {
      if (Objects.equals(account.getIdAccount(), idMyAccount)) {
        account.withdrawal(money, LogTypes.Withdrawal, "");
        break;
      }
    }
  }

  public void replenishment(String idMyAccount, float money)
      throws TransactionCannotBeMade, AccountNotFoundException {
    checkAccount(idMyAccount);
    for (Account account :
        accounts) {
      if (Objects.equals(account.getIdAccount(), idMyAccount)) {
        account.replenishment(money, LogTypes.Replenishment, "");
        break;
      }
    }
  }

  public void makeTransfer(String idMyAccount, String idReceiverAccount, float money)
      throws AccountNotFoundException, TransactionCannotBeMade {
    checkAccount(idMyAccount);
    Transfer.getInstance().makeTransfer(idMyAccount, idReceiverAccount, money);
  }

  public void cancelTransfer(String idMyAccount, int idTransaction)
      throws ErrorCancelTransaction, TransactionNotFoundException, AccountNotFoundException, TransactionCannotBeMade {
    checkAccount(idMyAccount);
    Transfer.getInstance().cancelTransfer(idMyAccount, idTransaction);
  }

  public void blockAccount(String idMyAccount) throws AccountNotFoundException {
    checkAccount(idMyAccount);
    for (Account account :
        accounts) {
      if (Objects.equals(account.getIdAccount(), idMyAccount)) {
        account.block();
        break;
      }
    }
  }

  public void update(Offer offer) {
    for (Account account :
        accounts) {
      if (offer.getClass() == account.getOffer().getClass()) {
        notifications.add(offer.getBehavior().getNotifications());
      }
    }
  }

  public void setFullName(String fullName, String password)
      throws IncorrectPassword, InvalidClientNameException {
    if (fullName == null) {
      throw new InvalidClientNameException();
    }
    if (this.password == null) {
      this.password = password;
    } else if (!this.password.equals(password)) {
      login(password);
    }

    this.fullName = fullName;
  }

  public void setPassport(String passport, String password)
      throws IncorrectPassword, InvalidPassportException {
    login(password);
    if (!passport.matches("\\d{2} \\d{2} \\d{6}")) {
      throw new InvalidPassportException();
    }

    if (!Objects.equals(this.password, password)) {
      throw new IncorrectPassword();
    }

    this.passport = passport;
    if (address == null) {
      return;
    }

    for (Account account :
        accounts) {
      account.standard();
    }
  }

  public void setAddress(String address, String password)
      throws InvalidAddressException, IncorrectPassword {
    login(password);
    if (address == null) {
      throw new InvalidAddressException();
    }

    this.address = address;
    if (passport == null) {
      return;
    }

    for (Account account :
        accounts) {
      account.standard();
    }
  }

  public void setPhoneNumber(String phoneNumber, String password)
      throws IncorrectPassword, InvalidPassportException {
    login(password);
    if (!phoneNumber.matches("^7\\d{10}")) {
      throw new InvalidPassportException();
    }

    this.phoneNumber = phoneNumber;
  }

  public void clearNotifications() {
    notifications = new ArrayList<>();
  }

  public void login(String password) throws IncorrectPassword {
    if (!Objects.equals(password, this.password)) {
      throw new IncorrectPassword();
    }
  }

  public void addAccount(Account account) {
    accounts.add(account);
  }

  private void checkAccount(String id) throws AccountNotFoundException {
    for (Account account :
        accounts) {
      if (Objects.equals(account.getIdAccount(), id)) {
        return;
      }
    }
    throw new AccountNotFoundException();
  }
}
