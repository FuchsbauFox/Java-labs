package ru.itmo.bankSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import ru.itmo.bankSystem.offers.DepositOffer;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;
import ru.itmo.tools.bankExceptions.AccountCannotBeCreatedException;
import ru.itmo.tools.bankExceptions.ClientCannotBeAttach;
import ru.itmo.tools.bankExceptions.ClientNotFoundException;
import ru.itmo.tools.bankExceptions.InvalidBankNameException;
import ru.itmo.tools.bankExceptions.OfferAlreadyExistException;
import ru.itmo.tools.bankExceptions.OfferNotExistException;
import ru.itmo.tools.bankExceptions.TypesInfoAccountDifferentException;
import ru.itmo.tools.clientExceptions.IncorrectPassword;

public class Bank {

  public int bankId;
  private List<Offer> offers;
  private List<Client> clients;
  private List<Client> observers;
  private String name;
  private int idLastAccount;

  public Bank(String name, int id) throws InvalidBankNameException {
    if (!name.matches("[А-ЯЁа-яёA-Za-z]*")) {
      throw new InvalidBankNameException();
    }

    this.name = name;
    this.bankId = id;
    this.idLastAccount = 0;
    this.offers = new ArrayList<Offer>();
    this.clients = new ArrayList<Client>();
    this.observers = new ArrayList<Client>();
  }

  public String getName() {
    return name;
  }

  public int getBankId() {
    return bankId;
  }

  public List<Offer> getOffers() {
    return Collections.unmodifiableList(offers);
  }

  public List<Client> getClients() {
    return Collections.unmodifiableList(clients);
  }

  public void addOffer(Offer info) throws OfferNotExistException, OfferAlreadyExistException {
    checkOfferOnExist(info, false);

    offers.add(info);
    notify(info);
  }

  public void changeOffer(Offer oldOffer, Offer newOffer)
      throws TypesInfoAccountDifferentException, OfferNotExistException, OfferAlreadyExistException {
    checkOfferOnExist(oldOffer, true);
    checkOfferOnExist(newOffer, false);
    if (oldOffer.getClass() != newOffer.getClass()) {
      throw new TypesInfoAccountDifferentException();
    }

    offers.remove(oldOffer);
    offers.add(newOffer);
    notify(newOffer);
  }

  public void makeAccount(Client client, Offer offer, String password)
      throws ClientNotFoundException, AccountCannotBeCreatedException, OfferNotExistException, OfferAlreadyExistException, IncorrectDateForDepositException, IncorrectPassword {
    makeAccount(client, offer, password, 1);
  }

  public void makeAccount(Client client, Offer offer, String password, int monthsForDeposit)
      throws AccountCannotBeCreatedException, ClientNotFoundException, OfferNotExistException, OfferAlreadyExistException, IncorrectDateForDepositException, IncorrectPassword {
    checkOfferOnExist(offer, true);
    checkClient(client);
    if (idLastAccount > 999999) {
      throw new AccountCannotBeCreatedException();
    }
    client.login(password);
    if (offer instanceof DepositOffer && monthsForDeposit == 0) {
      throw new AccountCannotBeCreatedException();
    }

    Account account = accountCreator(offer,
        client.getPassport() == null || client.getAddress() == null,
        monthsForDeposit);

    client.addAccount(account);
  }

  public void attach(Client client) throws ClientCannotBeAttach {
    if (client.getPhoneNumber() == null) {
      throw new ClientCannotBeAttach();
    }
    observers.add(client);
  }

  public void detach(Client client) {
    observers.remove(client);
  }

  public Client findClient(String fullName) {
    for (Client client :
        clients) {
      if (Objects.equals(client.getFullName(), fullName)) {
        return client;
      }
    }

    return null;
  }

  private void notify(Offer offer) {
    for (Client observer :
        observers) {
      observer.update(offer);
    }
  }

  private Account accountCreator(Offer offer, boolean isDoubtful)
      throws IncorrectDateForDepositException {
    return accountCreator(offer, isDoubtful, 0);
  }

  private Account accountCreator(Offer offer, boolean isDoubtful, int monthsForDeposit)
      throws IncorrectDateForDepositException {
    DecimalFormat formatBankId = new DecimalFormat("000");
    DecimalFormat formatIdAccount = new DecimalFormat("000000");
    String id = formatBankId.format(bankId) + " " + formatIdAccount.format(idLastAccount);
    idLastAccount++;
    return offer.getBehavior().createAccount(offer, id, isDoubtful, monthsForDeposit);
  }


  private void checkOfferOnExist(Offer offer, boolean shouldExist)
      throws OfferNotExistException, OfferAlreadyExistException {
    boolean offerExist = false;
    for (Offer thisOffer :
        offers) {
      if (thisOffer == offer) {
        offerExist = true;
        break;
      }
    }

    if (!shouldExist && offerExist) {
      throw new OfferAlreadyExistException();
    }
    if (shouldExist && !offerExist) {
      throw new OfferNotExistException();
    }
  }

  private void checkClient(Client client) throws ClientNotFoundException {
    if (client.getAccounts().size() == 0) {
      clients.add(client);
      return;
    }

    boolean flag = true;
    for (Client thisClient :
        clients) {
      if (thisClient == client) {
        return;
      }
    }
    throw new ClientNotFoundException();
  }
}
