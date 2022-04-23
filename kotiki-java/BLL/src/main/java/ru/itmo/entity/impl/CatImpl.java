package ru.itmo.entity.impl;

import java.util.Date;
import ru.itmo.accessory.Color;
import ru.itmo.entity.Cat;

public class CatImpl implements Cat {
  private int id;
  private String name;
  private Date dateOfBirth;
  private String breed;
  private Color color;
  private int ownerId;

  public CatImpl(int id, String name, Date dateOfBirth, String breed, Color color, int ownerId) {
    this.id = id;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.breed = breed;
    this.color = color;
    this.ownerId = ownerId;
  }

  public CatImpl(String name, Date dateOfBirth, String breed, Color color) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.breed = breed;
    this.color = color;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }
}
