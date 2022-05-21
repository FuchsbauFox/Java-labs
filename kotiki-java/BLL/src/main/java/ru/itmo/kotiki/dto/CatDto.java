package ru.itmo.kotiki.dto;

import java.util.Date;
import ru.itmo.kotiki.model.Cat;
import ru.itmo.kotiki.model.accessory.Color;

public class CatDto {
  private String name;
  private Date dateOfBirth;
  private String breed;
  private String color;

  public Cat toCat() {
    return new Cat(name, dateOfBirth, breed, Color.valueOf(color));
  }

  public CatDto fromCat(Cat cat) {
    this.name = cat.getName();
    this.dateOfBirth = cat.getDateOfBirth();
    this.breed = cat.getBreed();
    this.color = cat.getColor().toString();
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}

