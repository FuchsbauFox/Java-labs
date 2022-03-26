package ru.itmo;

import ru.itmo.tool.accountException.TransactionCannotBeMade;
import ru.itmo.ui.UiMain;

public class Program {

  public static void main(String[] args) throws TransactionCannotBeMade {
    UiMain.getInstance().start();
  }
}
