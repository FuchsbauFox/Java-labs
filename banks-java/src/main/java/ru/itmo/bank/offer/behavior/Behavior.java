package ru.itmo.bank.offer.behavior;

import ru.itmo.bank.Account;
import ru.itmo.bank.Offer;
import ru.itmo.tool.accountException.IncorrectDateForDepositException;

public interface Behavior {

  Account createAccount(Offer offer, String id, boolean isDoubtful, int monthsForDeposit)
      throws IncorrectDateForDepositException;

  String getNotifications();
}
