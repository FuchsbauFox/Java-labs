package ru.itmo.ui.states;

import java.util.Scanner;
import ru.itmo.bank.Bank;
import ru.itmo.bank.CentralBank;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.tools.bankExceptions.BankAlreadyExistException;
import ru.itmo.tools.bankExceptions.BankNotExistException;
import ru.itmo.ui.UiAdapter;
import ru.itmo.ui.UiMain;
import ru.itmo.ui.UiState;

public class BankState extends UiState {

  private Bank bank;
  private Scanner in = new Scanner(System.in);

  public BankState(UiMain uiMain) throws BankAlreadyExistException, BankNotExistException {
    System.out.println("Bank name: ");
    bank = CentralBank.getInstance().getBank(in.next());
  }

  public void start() throws TransactionCannotBeMade {
    while (true) {
      System.out.println("Enter command: ");
      String command = in.next();
      switch (command) {
        case "showClients" -> UiAdapter.getInstance().showClients(bank);
        case "showOffers" -> UiAdapter.getInstance().showOffers(bank);
        case "makeAccount" -> UiAdapter.getInstance().makeAccount(bank);
        case "addOffer" -> UiAdapter.getInstance().addOffer(bank);
        case "attach" -> UiAdapter.getInstance().attach(bank);
        case "detach" -> UiAdapter.getInstance().detach(bank);
        case "logout" -> {
          uiMain.transitionTo(new MainState());
          uiMain.start();
        }
        case "help" -> help();
        default -> System.out.println("Error: unknown command");
      }
    }
  }

  public void help() {
    System.out.println("addOffer - add offer in the bank");
    System.out.println("makeAccount - create or found client with account in the bank");
    System.out.println("showOffers - show offers at the bank");
    System.out.println("showClients - show clients at the bank");
    System.out.println("attach - subscribe client for notifications of new accounts");
    System.out.println("detach - unsubscribe client from notifications of new accounts");
    System.out.println("logout - return in main menu");
  }
}
