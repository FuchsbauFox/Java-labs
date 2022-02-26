package ru.itmo;

import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.ui.UiMain;

public class Program {

  public static void main(String[] args) throws TransactionCannotBeMade {
    UiMain.getInstance().start();
  }
}
