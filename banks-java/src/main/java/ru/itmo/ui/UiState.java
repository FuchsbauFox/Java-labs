package ru.itmo.ui;

import ru.itmo.tool.accountException.TransactionCannotBeMade;

public abstract class UiState {

  protected UiMain uiMain;

  public void setContext(UiMain ui) {
    uiMain = ui;
  }

  public UiMain getUiMain() {
    return uiMain;
  }

  public abstract void start() throws TransactionCannotBeMade;

  public abstract void help();
}
