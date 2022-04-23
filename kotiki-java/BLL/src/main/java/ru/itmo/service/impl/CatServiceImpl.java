package ru.itmo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.itmo.entity.Cat;
import ru.itmo.entity.impl.CatImpl;
import ru.itmo.model.CatDb;
import ru.itmo.repository.CatRepository;
import ru.itmo.service.CatService;

@Service
@EntityScan(basePackages = {"ru.itmo.*"})
@ComponentScan({"ru.itmo.repository", "ru.itmo.service", "ru.itmo.service.impl"})
public class CatServiceImpl implements CatService {

  private final CatRepository catRepository;

  @Autowired
  public CatServiceImpl(CatRepository catRepository) {
    this.catRepository = catRepository;
  }

  public List<Cat> getCats() {
    List<CatDb> catsDb = catRepository.findAll();
    List<Cat> cats = new ArrayList<>();
    for (CatDb cat :
        catsDb) {
      cats.add(new CatImpl(
          cat.getId(),
          cat.getName(),
          cat.getDateOfBirth(),
          cat.getBreed(),
          cat.getColor(),
          cat.getOwner().getId()));
    }
    return cats;
  }

  public Cat getCat(int id) {
    CatDb catDb = catRepository.getById(id);
    return new CatImpl(
        catDb.getId(),
        catDb.getName(),
        catDb.getDateOfBirth(),
        catDb.getBreed(),
        catDb.getColor(),
        catDb.getOwner().getId());
  }

  public void saveCat(Cat cat) {
    CatDb catDb = new CatDb(cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor());
    catRepository.save(catDb);
  }

  public void deleteCat(Cat cat) {
    CatDb catDb = new CatDb(cat.getName(), cat.getDateOfBirth(), cat.getBreed(), cat.getColor());
    catRepository.delete(catDb);
  }

  public void updateCat(Cat cat) {
    //repository.update(cat)
  }
}
