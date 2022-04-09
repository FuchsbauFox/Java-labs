import static org.mockito.Mockito.mock;

import accessory.color;
import inter.CatService;
import inter.OwnerService;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.Cat;
import model.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.testng.annotations.AfterMethod;
import service.CatServiceImpl;
import service.OwnerServiceImpl;

public class CatsTest {

  @Mock
  CatService catService;
  @Mock
  OwnerService ownerService;

  static MockitoSession session;

  @BeforeEach
  public void Before() {
    session = Mockito.mockitoSession()
        .initMocks(this)
        .startMocking();
    ownerService = mock(OwnerServiceImpl.class);
    catService = mock(CatServiceImpl.class);
  }

  @Test
  public void AddOwnerAndCat() {
    Owner owner = new Owner("Vova", new GregorianCalendar(2002, Calendar.JUNE, 7).getTime());
    Cat cat1 = new Cat("Lisa", new GregorianCalendar(2011, Calendar.MAY, 10).getTime(),
        "European Shorthair", color.RED_TABBY);
    Cat cat2 = new Cat("Jula", new GregorianCalendar(2007, Calendar.MAY, 10).getTime(),
        "European Shorthair", color.RED_TABBY);

    owner.addCat(cat1);
    owner.addCat(cat2);

    ownerService.saveOwner(owner);
    catService.saveCat(cat1);
    catService.saveCat(cat2);

    Mockito.when(ownerService.findOwner(1)).thenReturn(owner);
    Mockito.when(catService.findCat(1)).thenReturn(cat1);
    Mockito.when(catService.findCat(2)).thenReturn(cat2);

    Assertions.assertEquals(ownerService.findOwner(1).getName(), "Vova");
    Assertions.assertEquals(catService.findCat(1).getName(), "Lisa");
    Assertions.assertEquals(catService.findCat(2).getName(), "Jula");

    cat2.setName("Julia");
    catService.updateCat(cat2);
    Assertions.assertEquals(catService.findCat(2).getName(), "Julia");
  }

  @AfterMethod
  public void afterMethod() {
    session.finishMocking();
  }
}
