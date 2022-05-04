import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.bank.Bank;
import ru.itmo.bank.CentralBank;
import ru.itmo.bank.Client;
import ru.itmo.bank.builder.ClientBuilder;
import ru.itmo.bank.builder.ClientDirector;
import ru.itmo.bank.offer.CreditOffer;
import ru.itmo.bank.offer.DebitOffer;
import ru.itmo.bank.offer.DepositOffer;
import ru.itmo.tool.accountException.AccountNotFoundException;
import ru.itmo.tool.accountException.ArgumentNullException;
import ru.itmo.tool.accountException.CommissionMoreThanLimitDoubtfulAccountException;
import ru.itmo.tool.accountException.FirstLimitDoesntStartAtZeroException;
import ru.itmo.tool.accountException.IncorrectDateForDepositException;
import ru.itmo.tool.accountException.ListsDontFitTogetherException;
import ru.itmo.tool.accountException.ListsMustNotDecrease;
import ru.itmo.tool.accountException.NegativeOrNilCommissionException;
import ru.itmo.tool.accountException.NegativeOrNilInterestException;
import ru.itmo.tool.accountException.NegativeOrNilLimitDoubtfulAccountException;
import ru.itmo.tool.accountException.NegativeOrNilLimitException;
import ru.itmo.tool.accountException.TransactionCannotBeMade;
import ru.itmo.tool.bankException.AccountCannotBeCreatedException;
import ru.itmo.tool.bankException.BankAlreadyExistException;
import ru.itmo.tool.bankException.BankCannotBeAddedException;
import ru.itmo.tool.bankException.BankNotExistException;
import ru.itmo.tool.bankException.ClientNotFoundException;
import ru.itmo.tool.bankException.InvalidBankNameException;
import ru.itmo.tool.bankException.OfferAlreadyExistException;
import ru.itmo.tool.bankException.OfferNotExistException;
import ru.itmo.tool.clientException.IncorrectPassword;
import ru.itmo.tool.clientException.InvalidAddressException;
import ru.itmo.tool.clientException.InvalidClientNameException;
import ru.itmo.tool.clientException.InvalidPassportException;

public class BankTest {
  private Bank bank1;
  private Bank bank2;
  private Client client1;
  private Client client2;

  @BeforeEach
  public void setUp()
      throws NegativeOrNilCommissionException, NegativeOrNilLimitException, NegativeOrNilLimitDoubtfulAccountException, CommissionMoreThanLimitDoubtfulAccountException, NegativeOrNilInterestException, ListsMustNotDecrease, FirstLimitDoesntStartAtZeroException, ArgumentNullException, ListsDontFitTogetherException, InvalidPassportException, InvalidClientNameException, IncorrectPassword, InvalidAddressException, OfferNotExistException, OfferAlreadyExistException, BankAlreadyExistException, BankNotExistException, InvalidBankNameException, BankCannotBeAddedException {

    CentralBank.getInstance().addBank("Сбербанк");
    CentralBank.getInstance().addBank("Газпромбанк");
    bank1 = CentralBank.getInstance().getBank("Сбербанк");
    bank2 = CentralBank.getInstance().getBank("Газпромбанк");

    List<Float> from = new ArrayList<Float>();
    from.add(0F);
    from.add(1000F);
    from.add(5000F);
    from.add(10000F);
    List<Float> interest = new ArrayList<Float>();
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
