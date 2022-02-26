package ru.itmo.ui.states;

import java.util.Scanner;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.ui.UiAdapter;
import ru.itmo.ui.UiState;

public class MainState extends UiState {

  private Scanner in = new Scanner(System.in);

  public void start() throws TransactionCannotBeMade {
    while (true) {
      System.out.println("Enter command: ");
      String command = in.next();
      switch (command) {
        case "help":
          help();
          break;
        case "manager":
          try {
            uiMain.transitionTo(new BankState(uiMain));
            uiMain.start();
          } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            uiMain.transitionTo(new MainState());
          }

          break;
        case "user":
          try {
            uiMain.transitionTo(new UserState(uiMain));
            uiMain.start();
          } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            uiMain.transitionTo(new MainState());
          }

          break;
        case "showBanks":
          UiAdapter.getInstance().showBanks();
          break;
        case "addBank":
          UiAdapter.getInstance().addBank();
          break;
        case "skipDay":
          UiAdapter.getInstance().skipDay();
          break;
        case "skipMonth":
          UiAdapter.getInstance().skipMonth();
          break;
        case "skipYear":
          UiAdapter.getInstance().skipYear();
          break;
        case "exit":
          return;
        default:
          System.out.println("Error: unknown command");
      }
    }
  }

  public void help() {
    System.out.println("addBank - add bank");
    System.out.println("showBanks - show registered banks");
    System.out.println("manager - login as manager");
    System.out.println("user - login as user");
    System.out.println("skipDay - skip one day");
    System.out.println("skipMonth - skip one month");
    System.out.println("skipYear - skip one year");
    System.out.println("exit - finish work");
  }
}
