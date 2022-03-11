package ru.itmo.bank.offers.behaviors;

import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;

public interface Behavior {

  Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit)
      throws IncorrectDateForDepositException;

  String getNotifications();
}
