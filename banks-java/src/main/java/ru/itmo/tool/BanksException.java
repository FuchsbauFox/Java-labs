package ru.itmo.tool;

public class BanksException extends RuntimeException {

  public BanksException() {
  }

  public BanksException(String exception) {
    super(exception);
  }
}
