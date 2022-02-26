package ru.itmo.bankSystem.clientBuilder;

import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.tools.clientExceptions.InvalidAddressException;
import ru.itmo.tools.clientExceptions.InvalidClientNameException;
import ru.itmo.tools.clientExceptions.InvalidPassportException;

public class Director {

  private Builder builder;

  public Director() {
    this.builder = null;
  }

  public Builder getBuilder() {
    return builder;
  }

  public void setBuilder(Builder builder) {
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
