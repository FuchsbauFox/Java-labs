package ru.itmo.ui.states;

import java.util.Scanner;
import ru.itmo.bankSystem.Bank;
import ru.itmo.bankSystem.CentralBank;
import ru.itmo.bankSystem.Client;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.tools.bankExceptions.BankAlreadyExistException;
import ru.itmo.tools.bankExceptions.BankNotExistException;
import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.ui.UiAdapter;
import ru.itmo.ui.UiMain;
import ru.itmo.ui.UiState;

public class UserState extends UiState {

  private Client client;
  private String password;
  private Scanner in = new Scanner(System.in);

  public UserState(UiMain uiMain)
      throws BankAlreadyExistException, BankNotExistException, IncorrectPassword {
    System.out.println("Bank(name) to which the client belongs: ");
    Bank bank = CentralBank.getInstance().getBank(in.next());
    System.out.println("Client full name: ");
    String fullName = in.next();
    System.out.println("Client password: ");
    password = in.next();

    client = bank.findClient(fullName);
    if (client == null) {
      // throw new ClientNotFoundException();
    }

    client.login(password);

    for (var notification :
        client.getNotifications()) {
      System.out.println(notification);
    }

    client.clearNotifications();
  }

  public void start() throws TransactionCannotBeMade {
    while (true) {
      System.out.println("Enter command: ");
      String command = in.next();
      switch (command) {
        case "help" -> help();
        case "fullName" -> UiAdapter.getInstance().newFullName(client, password);
        case "passport" -> UiAdapter.getInstance().newPassport(client, password);
        case "address" -> UiAdapter.getInstance().newAddress(client, password);
        case "phone" -> UiAdapter.getInstance().newPhoneNumber(client, password);
        case "showAccounts" -> UiAdapter.getInstance().showAccounts(client);
        case "withdrawal" -> UiAdapter.getInstance().withdrawal(client);
        case "replenishment" -> UiAdapter.getInstance().replenishment(client);
        case "transfer" -> UiAdapter.getInstance().transfer(client);
        case "log" -> UiAdapter.getInstance().showLog(client);
        case "cancelTransfer" -> UiAdapter.getInstance().cancelTransfer(client);
        case "logout" -> {
          uiMain.transitionTo(new MainState());
          uiMain.start();
        }
        default -> System.out.println("Error: unknown command");
      }
    }
  }

  public void help() {
    System.out.println("fullName - set new full name");
    System.out.println("passport - set new passport");
    System.out.println("address - set new address");
    System.out.println("phone - set new phone number");
    System.out.println("showAccounts - show client accounts");
    System.out.println("withdrawal - ATM withdrawal");
    System.out.println("replenishment - ATM replenishment");
    System.out.println("transfer - transfer money to another account");
    System.out.println("log - show log operations with account");
    System.out.println("cancelTransfer - cancel transfer money");
    System.out.println("logout- return in main menu");
  }
}
