package ru.itmo.bank.builder;

import ru.itmo.bank.Client;
import ru.itmo.tool.clientException.IncorrectPassword;
import ru.itmo.tool.clientException.InvalidAddressException;
import ru.itmo.tool.clientException.InvalidClientNameException;
import ru.itmo.tool.clientException.InvalidPassportException;

public class ClientBuilder {

  private Client _client;

  public ClientBuilder() {
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
