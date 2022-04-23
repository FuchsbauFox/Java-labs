package ru.itmo.entity.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.itmo.entity.Cat;
import ru.itmo.entity.Owner;

public class OwnerImpl implements Owner {
  private int id;
  private String name;
  private Date dateOfBirth;
  private List<Cat> cats;

  public OwnerImpl(int id, String name, Date dateOfBirth, List<Cat> cats) {
    this.id = id;
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.cats = new ArrayList<>(cats);
  }

  public OwnerImpl(String name, Date dateOfBirth) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
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

  public List<Cat> getCats() {
    return cats;
  }

  public void setCats(List<Cat> cats) {
    this.cats = cats;
  }
}
