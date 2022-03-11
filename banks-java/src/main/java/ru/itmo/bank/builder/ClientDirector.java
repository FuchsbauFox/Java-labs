package ru.itmo.bank.builder;

import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.tools.clientExceptions.InvalidAddressException;
import ru.itmo.tools.clientExceptions.InvalidClientNameException;
import ru.itmo.tools.clientExceptions.InvalidPassportException;

public class ClientDirector {

  private ClientBuilder builder;

  public ClientDirector() {
    this.builder = null;
  }

  public ClientBuilder getBuilder() {
    return builder;
  }

  public void setBuilder(ClientBuilder builder) {
    this.builder = builder;
  }

  public void buildClient(
      String fullName,
      String password,
      String passport,
      String address,
      String phoneNumber)
      throws InvalidClientNameException, IncorrectPassword, InvalidPassportException, InvalidAddressException {
    builder.setFullName(fullName, password);
    if (passport != null) {
      builder.setPassport(passport, password);
    }
    if (address != null) {
      builder.setAddress(address, password);
    }
    if (phoneNumber != null) {
      builder.setPhoneNumber(phoneNumber, password);
    }
  }
}
