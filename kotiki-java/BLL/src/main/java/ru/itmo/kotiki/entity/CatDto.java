package ru.itmo.kotiki.entity;

import java.util.Date;

public class CatDto {

  private final int id;
  private final String name;
  private final Date dateOfBirth;
  private final String breed;
  private final String color;
  private final int ownerId;

  public CatDto(int id, String name, Date dateOfBirth, String breed, String color, int ownerId) {
    this.id = id;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.breed = breed;
    this.color = color;
    this.ownerId = ownerId;
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

  public String getBreed() {
    return breed;
  }

  public String getColor() {
    return color;
  }

  public int getOwnerId() {
    return ownerId;
  }
}

