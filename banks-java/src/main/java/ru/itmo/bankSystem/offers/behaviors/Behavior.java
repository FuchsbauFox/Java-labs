package ru.itmo.bankSystem.offers.behaviors;

import ru.itmo.bankSystem.Account;
import ru.itmo.bankSystem.Offer;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;

public interface Behavior {

  Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit)
      throws IncorrectDateForDepositException;

  String getNotifications();
}
