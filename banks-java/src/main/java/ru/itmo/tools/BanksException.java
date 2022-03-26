package ru.itmo.tools;

public class BanksException extends RuntimeException {

  public BanksException() {
  }

  public BanksException(String exception) {
    super(exception);
  }
}
