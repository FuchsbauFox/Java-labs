package ru.itmo.ui;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import ru.itmo.bankSystem.Bank;
import ru.itmo.bankSystem.CentralBank;
import ru.itmo.bankSystem.Client;
import ru.itmo.bankSystem.Offer;
import ru.itmo.bankSystem.accounts.DepositAccount;
import ru.itmo.bankSystem.clientBuilder.Builder;
import ru.itmo.bankSystem.clientBuilder.Director;
import ru.itmo.bankSystem.offers.CreditOffer;
import ru.itmo.bankSystem.offers.DebitOffer;
import ru.itmo.bankSystem.offers.DepositOffer;
import ru.itmo.tools.MyCalendar;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.tools.bankExceptions.ClientNotFoundException;
import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.tools.clientExceptions.InvalidAddressException;
import ru.itmo.tools.clientExceptions.InvalidClientNameException;
import ru.itmo.tools.clientExceptions.InvalidPassportException;
import ru.itmo.tools.uiExceptions.UnknownCommand;

public class UiAdapter {

  private static UiAdapter instance;
  private Scanner in = new Scanner(System.in);

  private UiAdapter() {
  }

  public static UiAdapter getInstance() {
    if (instance == null) {
      instance = new UiAdapter();
    }
    return instance;
  }

  public void showBanks() {
    for (var bank :
        CentralBank.getInstance().getBanks()) {
      out.println("Id: " + bank.getBankId() + "\t" + "Bank: " + bank.getName());
    }
  }

  public void addBank() {
    try {
      System.out.println("Bank name: ");
      String name = in.nextLine();
      if (name == null) {
        // throw new ArgumentException();
      }
      CentralBank.getInstance().addBank(name);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void skipDay() throws TransactionCannotBeMade {
    MyCalendar.getInstance().skipDay();
  }

  public void skipMonth() throws TransactionCannotBeMade {
    MyCalendar.getInstance().skipMonth();
  }

  public void skipYear() throws TransactionCannotBeMade {
    MyCalendar.getInstance().skipYear();
  }

  public void showClients(Bank bank) {
    int counter = 1;
    for (var client :
        bank.getClients()) {
      System.out.println("==== " + counter + " ====");
      counter++;
      System.out.println("FullName: " + client.getFullName());
      System.out.println("Passport: " + client.getPassport());
      System.out.println("Address: " + client.getAddress());
      System.out.println("Phone number: " + client.getPhoneNumber());
      showAccounts(client);
    }
  }

  public void showOffers(Bank bank) {
    int counter = 1;
    for (var offer :
        bank.getOffers()) {
      System.out.println("==== " + counter + " ====");
      counter++;
      offerOutput(offer);
    }
  }

  public void makeAccount(Bank bank) {
    try {
      int numberOfMonths = 0;
      System.out.println("Client full name: ");
      String fullName = in.nextLine();
      System.out.println("Client password: ");
      String password = in.nextLine();
      Offer offer = getOffer(bank);
      Client client = bank.findClient(fullName);
      if (client == null) {
        createClient(fullName, password);
      }

      if (offer instanceof DepositOffer) {
        System.out.println("Current date: " + MyCalendar.getInstance().getCalendar());
        System.out.println("Number of months for which the deposit is provided: ");
        numberOfMonths = in.nextInt();
      }

      bank.makeAccount(client, offer, password, numberOfMonths);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void addOffer(Bank bank) {
    try {
      System.out.println("Enter type offer: ");
      String type = in.nextLine();
      switch (type) {
        case "credit" -> {
          System.out.println("Enter commission: ");
          float commission = in.nextFloat();
          System.out.println("Enter limit: ");
          float limit = in.nextFloat();
          System.out.println("Enter limit doubtful account: ");
          float limitDoubtfulAccount = in.nextFloat();

          bank.addOffer(new CreditOffer(commission, limit, limitDoubtfulAccount));
        }
        case "debit" -> {
          System.out.println("Enter interest on balance: ");
          float interestOnBalance = in.nextFloat();
          System.out.println("Enter limit doubtful account: ");
          float limitDoubtfulAccount = in.nextFloat();

          bank.addOffer(new DebitOffer(interestOnBalance, limitDoubtfulAccount));
        }
        case "deposit" -> {
          System.out.println("Enter interest on balance: ");
          var fromList = new ArrayList<Float>();
          var interestList = new ArrayList<Float>();
          while (true) {
            System.out.println("From: ");
            float from = in.nextFloat();
            fromList.add(from);
            System.out.println("Interest: ");
            float interest = in.nextFloat();
            interestList.add(interest);

            System.out.println("Continue enter?");
            boolean exit = in.nextBoolean();
            if (!exit) {
              break;
            }
          }

          System.out.println("Enter limit doubtful account: ");
          float limitDoubtfulAccount = in.nextFloat();

          bank.addOffer(new DepositOffer(fromList, interestList, limitDoubtfulAccount));
        }
        default -> System.out.println("Error: account type unknown");
      }
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void attach(Bank bank) {
    try {
      System.out.println("Client full name: ");
      String fullName = in.nextLine();
      System.out.println("Client password: ");
      String password = in.nextLine();
      Client client = bank.findClient(fullName);
      if (client == null) {
        throw new ClientNotFoundException();
      }
      client.login(password);
      bank.attach(client);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void detach(Bank bank) {
    try {
      System.out.println("Client full name: ");
      String fullName = in.nextLine();
      System.out.println("Client password: ");
      String password = in.nextLine();
      Client client = bank.findClient(fullName);
      if (client == null) {
        throw new ClientNotFoundException();
      }
      client.login(password);
      bank.detach(client);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void showAccounts(Client client) {
    System.out.println("Accounts: ");
    int counter = 1;
    for (var account :
        client.getAccounts()) {
      System.out.println("==== " + counter + " ====");
      counter++;

      System.out.println("Id: " + account.getIdAccount());
      System.out.println("Money: " + account.getMoney());
      if (account.getOffer() instanceof DepositOffer) {
        System.out.println("Date deposit: " + ((DepositAccount) account).getDepositEndDate());
      }
      offerOutput(account.getOffer());
      System.out.println("Date created: " + account.getOpenDate());
    }
  }

  public void newFullName(Client client, String password) {
    try {
      System.out.println("New full name: ");
      client.setFullName(in.nextLine(), password);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void newPassport(Client client, String password) {
    try {
      System.out.println("New passport: ");
      client.setPassport(in.nextLine(), password);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void newAddress(Client client, String password) {
    try {
      System.out.println("New address: ");
      client.setAddress(in.nextLine(), password);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void newPhoneNumber(Client client, String password) {
    try {
      System.out.println("New phone number: ");
      client.setPhoneNumber(in.nextLine(), password);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void withdrawal(Client client) {
    try {
      System.out.println("Enter account id FROM which you want to withdraw money: ");
      String idMyAccount = in.nextLine();
      System.out.println("Money: ");
      float money = in.nextFloat();
      client.withdrawal(idMyAccount, money);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void replenishment(Client client) {
    try {
      System.out.println("Enter account id TO which you want to replenishment money: ");
      String idMyAccount = in.nextLine();
      System.out.println("Money: ");
      float money = in.nextFloat();
      client.replenishment(idMyAccount, money);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void transfer(Client client) {
    try {
      System.out.println("Enter account id FROM which you want to transfer money: ");
      String idMyAccount = in.nextLine();
      System.out.println("Enter account id TO which you want to transfer money: ");
      String idReceiverAccount = in.nextLine();
      System.out.println("Money: ");
      float money = in.nextFloat();
      client.makeTransfer(idMyAccount, idReceiverAccount, money);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void showLog(Client client) {
    try {
      System.out.println("Enter account id: ");
      String idMyAccount = in.nextLine();

      for (var account :
          client.getAccounts()) {
        if (Objects.equals(account.getIdAccount(), idMyAccount)) {
          for (var log :
              account.getTransactions()) {
            System.out.println(
                log.getIdTransaction() + ":\t[yellow]" + log.getType() + "[/]\t[green]"
                    + log.getMoney() + "[/]\n");
          }
        }
      }
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  public void cancelTransfer(Client client) {
    try {
      System.out.println("Enter account id: ");
      String idMyAccount = in.nextLine();
      System.out.println("Enter id transaction: ");
      int id = in.nextInt();
      client.cancelTransfer(idMyAccount, id);
    } catch (Exception exception) {
      System.out.println("Error: " + exception.getMessage());
    }
  }

  private Client createClient(String fullName, String password)
      throws InvalidPassportException, InvalidClientNameException, IncorrectPassword, InvalidAddressException {
    var builder = new Builder();
    var director = new Director();
    director.setBuilder(builder);
    System.out.println("If you do not want to enter data, then enter -");

    System.out.println("Client passport: ");
    String passport = in.nextLine();
    if (Objects.equals(passport, "-")) {
      passport = null;
    }

    System.out.println("Client address: ");
    String address = in.nextLine();
    if (Objects.equals(address, "-")) {
      address = null;
    }

    System.out.println("Client phone number: ");
    String phoneNumber = in.nextLine();
    if (Objects.equals(phoneNumber, "-")) {
      phoneNumber = null;
    }

    director.buildClient(fullName, password, passport, address, phoneNumber);
    return builder.getClient();
  }

  private Offer getOffer(Bank bank) throws UnknownCommand {
    System.out.println("Enter the offer number: ");
    String str = in.nextLine();
    int number = in.nextInt();
    if (number > bank.getOffers().size()) {
      throw new UnknownCommand();
    }
    return bank.getOffers().get(number - 1);
  }

  private void offerOutput(Offer offer) {
    if (offer instanceof CreditOffer account1) {
      out.println("Offer credit account: ");
      out.println("Commission: " + account1.getCommission());
      out.println("Limit: " + account1.getLimit());
      out.println("Limit doubtful account: " + account1.getLimitDoubtfulAccount());
    } else if (offer instanceof DebitOffer account2) {
      out.println("Offer debit account: ");
      out.println("Interest on balance: " + account2.getInterestOnBalance());
      out.println("Limit doubtful account: " + account2.getLimitDoubtfulAccount());
    } else if (offer instanceof DepositOffer account3) {
      out.println("Offer deposit account: ");
      out.println("Interest on balance: ");
      for (var item :
          account3.getInterests()) {
        out.println("From: " + item.getFrom() + "\tinterest: " + item.getInterest());
      }
      out.println("Limit doubtful account: " + account3.getLimitDoubtfulAccount());
    }
  }
}
