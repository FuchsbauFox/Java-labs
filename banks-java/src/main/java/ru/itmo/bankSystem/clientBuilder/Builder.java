package ru.itmo.bankSystem.clientBuilder;

import ru.itmo.bankSystem.Client;
import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.tools.clientExceptions.InvalidAddressException;
import ru.itmo.tools.clientExceptions.InvalidClientNameException;
import ru.itmo.tools.clientExceptions.InvalidPassportException;

public class Builder {

  private Client _client;

  public Builder() {
    reset();
  }

  public void setFullName(String fullName, String password)
      throws InvalidClientNameException, IncorrectPassword {
    _client.setFullName(fullName, password);
  }

  public void setPassport(String passport, String password)
      throws InvalidPassportException, IncorrectPassword {
    _client.setPassport(passport, password);
  }

  public void setAddress(String address, String password)
      throws InvalidAddressException, IncorrectPassword {
    _client.setAddress(address, password);
  }

  public void setPhoneNumber(String phoneNumber, String password)
      throws InvalidPassportException, IncorrectPassword {
    _client.setPhoneNumber(phoneNumber, password);
  }

  public Client getClient() {
    Client client = _client;
    reset();
    return client;
  }

  private void reset() {
    _client = new Client();
  }
}
