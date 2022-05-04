package ru.itmo.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import ru.itmo.tool.bankException.BankAlreadyExistException;
import ru.itmo.tool.bankException.BankCannotBeAddedException;
import ru.itmo.tool.bankException.BankNotExistException;
import ru.itmo.tool.bankException.InvalidBankNameException;

public class CentralBank {

  private static CentralBank instance;
  private final List<Bank> banks;
  private int idLastBank;

  private CentralBank() {
    banks = new ArrayList<>();
    idLastBank = 0;
  }

  public List<Bank> getBanks() {
    return Collections.unmodifiableList(banks);
  }

  public static CentralBank getInstance() {
    if (instance == null) {
      instance = new CentralBank();
    }
    return instance;
  }

  public void addBank(String name)
      throws BankAlreadyExistException, BankNotExistException, BankCannotBeAddedException, InvalidBankNameException {
    if (idLastBank > 999) {
      throw new BankCannotBeAddedException();
    }
    checkBankOnExist(name, false);
    banks.add(new Bank(name, idLastBank));
    idLastBank++;
  }

  public Bank getBank(String name) throws BankAlreadyExistException, BankNotExistException {
    checkBankOnExist(name, true);
    for (Bank bank :
        banks) {
      if (Objects.equals(bank.getName(), name)) {
        return bank;
      }
    }

    return null;
  }

  private void checkBankOnExist(String name, boolean shouldExist)
      throws BankAlreadyExistException, BankNotExistException {
    boolean bankExist = false;
    for (Bank bank :
        banks) {
      if (Objects.equals(bank.getName(), name)) {
        bankExist = true;
        break;
      }
    }

    if (!shouldExist && bankExist) {
      throw new BankAlreadyExistException();
    }
    if (shouldExist && !bankExist) {
      throw new BankNotExistException();
    }
  }
}
