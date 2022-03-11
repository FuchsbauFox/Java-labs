import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.bank.Bank;
import ru.itmo.bank.CentralBank;
import ru.itmo.bank.Client;
import ru.itmo.bank.builder.ClientBuilder;
import ru.itmo.bank.builder.ClientDirector;
import ru.itmo.bank.offers.CreditOffer;
import ru.itmo.bank.offers.DebitOffer;
import ru.itmo.bank.offers.DepositOffer;
import ru.itmo.tools.accountExceptions.AccountNotFoundException;
import ru.itmo.tools.accountExceptions.ArgumentNullException;
import ru.itmo.tools.accountExceptions.CommissionMoreThanLimitDoubtfulAccountException;
import ru.itmo.tools.accountExceptions.FirstLimitDoesntStartAtZeroException;
import ru.itmo.tools.accountExceptions.IncorrectDateForDepositException;
import ru.itmo.tools.accountExceptions.ListsDontFitTogetherException;
import ru.itmo.tools.accountExceptions.ListsMustNotDecrease;
import ru.itmo.tools.accountExceptions.NegativeOrNilCommissionException;
import ru.itmo.tools.accountExceptions.NegativeOrNilInterestException;
import ru.itmo.tools.accountExceptions.NegativeOrNilLimitDoubtfulAccountException;
import ru.itmo.tools.accountExceptions.NegativeOrNilLimitException;
import ru.itmo.tools.accountExceptions.TransactionCannotBeMade;
import ru.itmo.tools.bankExceptions.AccountCannotBeCreatedException;
import ru.itmo.tools.bankExceptions.BankAlreadyExistException;
import ru.itmo.tools.bankExceptions.BankCannotBeAddedException;
import ru.itmo.tools.bankExceptions.BankNotExistException;
import ru.itmo.tools.bankExceptions.ClientNotFoundException;
import ru.itmo.tools.bankExceptions.InvalidBankNameException;
import ru.itmo.tools.bankExceptions.OfferAlreadyExistException;
import ru.itmo.tools.bankExceptions.OfferNotExistException;
import ru.itmo.tools.clientExceptions.IncorrectPassword;
import ru.itmo.tools.clientExceptions.InvalidAddressException;
import ru.itmo.tools.clientExceptions.InvalidClientNameException;
import ru.itmo.tools.clientExceptions.InvalidPassportException;

public class BankTest {
  private Bank bank1;
  private Bank bank2;
  private Client client1;
  private Client client2;
  private Client client3;

  @BeforeEach
  public void setUp()
      throws NegativeOrNilCommissionException, NegativeOrNilLimitException, NegativeOrNilLimitDoubtfulAccountException, CommissionMoreThanLimitDoubtfulAccountException, NegativeOrNilInterestException, ListsMustNotDecrease, FirstLimitDoesntStartAtZeroException, ArgumentNullException, ListsDontFitTogetherException, InvalidPassportException, InvalidClientNameException, IncorrectPassword, InvalidAddressException, OfferNotExistException, OfferAlreadyExistException, BankAlreadyExistException, BankNotExistException, InvalidBankNameException, BankCannotBeAddedException {

    CentralBank.getInstance().addBank("Сбербанк");
    CentralBank.getInstance().addBank("Газпромбанк");
    bank1 = CentralBank.getInstance().getBank("Сбербанк");
    bank2 = CentralBank.getInstance().getBank("Газпромбанк");

    ArrayList<Float> from = new ArrayList<Float>();
    from.add(0F);
    from.add(1000F);
    from.add(5000F);
    from.add(10000F);
    ArrayList<Float> interest = new ArrayList<Float>();
    interest.add(4.5F);
    interest.add(5F);
    interest.add(6F);
    interest.add((float) 6.3);

    bank1.addOffer(new CreditOffer(5.5F, 10000, 5000));
    bank1.addOffer(new DebitOffer((float) 12.3, 10000));
    bank1.addOffer(new DepositOffer(from, interest, 10000));
    bank2.addOffer(new CreditOffer(5.5F, 10000, 5000));
    bank2.addOffer(new DebitOffer((float) 12.3, 10000));
    bank2.addOffer(new DepositOffer(from, interest, 10000));

    ClientBuilder builder = new ClientBuilder();
    ClientDirector director = new ClientDirector();
    director.setBuilder(builder);
    director.buildClient("Лыв Вацу", "123", null, null, null);
    client1 = builder.getClient();
    director.buildClient("Цу Вацу", "456", "12 12 121212", "asfe", "71234567891");
    client2 = builder.getClient();
    director.buildClient("Ми Вацу", "789", "12 12 121212", "asfe", "71234567891");
    client3 = builder.getClient();
  }

  @Test
  public void accountReplenishmentAndWithdrawal()
      throws ClientNotFoundException, AccountCannotBeCreatedException, IncorrectDateForDepositException, OfferNotExistException, OfferAlreadyExistException, IncorrectPassword, AccountNotFoundException, TransactionCannotBeMade {

    bank1.makeAccount(client1, bank1.getOffers().get(0), "123");
    client1.replenishment(client1.getAccounts().get(0).getIdAccount(), 5000);
    client1.withdrawal(client1.getAccounts().get(0).getIdAccount(), 1000);
  }

  @Test
  public void interbankTransfer()
      throws ClientNotFoundException, AccountCannotBeCreatedException, IncorrectDateForDepositException, OfferNotExistException, OfferAlreadyExistException, IncorrectPassword, AccountNotFoundException, TransactionCannotBeMade {
    bank1.makeAccount(client1, bank1.getOffers().get(0), "123");
    bank1.makeAccount(client2, bank1.getOffers().get(0), "456");
    client1.replenishment(client1.getAccounts().get(0).getIdAccount(), 5000);
    client1.makeTransfer("000 000000", "000 000001", 1000);
  }

  @Test
  public void transferBetweenOtherBanks()
      throws ClientNotFoundException, AccountCannotBeCreatedException, IncorrectDateForDepositException, OfferNotExistException, OfferAlreadyExistException, IncorrectPassword, AccountNotFoundException, TransactionCannotBeMade {
    bank1.makeAccount(client1, bank1.getOffers().get(0), "123");
    bank2.makeAccount(client2, bank2.getOffers().get(0), "456");
    client1.replenishment(client1.getAccounts().get(0).getIdAccount(), 5000);
    client1.makeTransfer("000 000000", "001 000000", 1000);
  }
}
