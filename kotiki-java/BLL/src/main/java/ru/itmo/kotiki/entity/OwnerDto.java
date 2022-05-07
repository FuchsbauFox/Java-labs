package ru.itmo.kotiki.entity;

import java.util.Date;
import java.util.List;

public class OwnerDto {

  private final int id;
  private final String name;
  private final Date dateOfBirth;
  private final List<Integer> catsId;

  public OwnerDto(int id, String name, Date dateOfBirth, List<Integer> catsId) {
    this.id = id;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.catsId = catsId;
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

  public List<Integer> getCatsId() {
    return catsId;
  }
}
