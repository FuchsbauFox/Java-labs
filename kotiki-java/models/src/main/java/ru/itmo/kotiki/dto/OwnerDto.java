package ru.itmo.kotiki.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.Owner;

public final class OwnerDto {
  private final int id;
  private final String name;
  private final Date dateOfBirth;
  private final List<CatDto> cats;
  private final int userId;

  public OwnerDto(Owner owner) {
    this.id = owner.getId();
    this.name = owner.getName();
    this.dateOfBirth = owner.getDateOfBirth();
    this.cats = new ArrayList<>();
    for(Cat cat : owner.getCats()) {
      this.cats.add(new CatDto(cat));
    }
    this.userId = owner.getUser().getId();
  }

  public OwnerDto(int id, String name, Date dateOfBirth,
      List<CatDto> cats, int userId) {
    this.id = id;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.cats = cats;
    this.userId = userId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public List<CatDto> getCats() {
    return cats;
  }

  public int getUserId() {
    return userId;
  }
}
