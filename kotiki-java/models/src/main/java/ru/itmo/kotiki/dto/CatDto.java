package ru.itmo.kotiki.dto;

import java.util.Date;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.accessory.Color;

public final class CatDto {
  private final String name;
  private final Date dateOfBirth;
  private final String breed;
  private final String color;

  public CatDto(Cat cat) {
    this.name = cat.getName();
    this.dateOfBirth = cat.getDateOfBirth();
    this.breed = cat.getBreed();
    this.color = cat.getColor().toString();
  }

  public CatDto(String name, Date dateOfBirth, String breed, String color) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.breed = breed;
    this.color = color;
  }

  public Cat toCat() {
    return new Cat(name, dateOfBirth, breed, Color.valueOf(color));
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
}

