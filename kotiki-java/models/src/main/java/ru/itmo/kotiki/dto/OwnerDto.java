package ru.itmo.kotiki.dto;

import java.util.Date;
import java.util.List;
import ru.itmo.kotiki.model.Owner;

public final class OwnerDto {
  private final String name;
  private final Date dateOfBirth;

  public OwnerDto(Owner owner) {
    this.name = owner.getName();
    this.dateOfBirth = owner.getDateOfBirth();
  }

  public OwnerDto(String name, Date dateOfBirth, List<CatDto> cats) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
  }

  public Owner toOwner() {
    return new Owner(name, dateOfBirth);
  }

  public String getName() {
    return name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }
}
