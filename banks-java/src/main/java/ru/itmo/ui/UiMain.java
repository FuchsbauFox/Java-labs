package ru.itmo.ui;

import ru.itmo.tool.accountException.TransactionCannotBeMade;
import ru.itmo.ui.states.MainState;

public class UiMain {

  private static UiMain instance;
  private UiState uiState;

  private UiMain() {
    uiState = new MainState();
    uiState.setContext(this);
  }

  public static UiMain getInstance() {
    if (instance == null) {
      instance = new UiMain();
    }
    return instance;
  }

  public void transitionTo(UiState uiState) {
    this.uiState = uiState;
    this.uiState.setContext(this);
  }

  public void start() throws TransactionCannotBeMade {
    uiState.start();
  }

  private void help() {
    uiState.help();
  }
}
