package ru.itmo.kotiki.entity;

import java.util.Date;
import java.util.List;

public class OwnerDto {

  private int id;
  private String name;
  private Date dateOfBirth;
  private List<Integer> catsId;

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

  public List<Integer> getCatsId() {
    return catsId;
  }

  public void setCatsId(List<Integer> catsId) {
    this.catsId = catsId;
  }
}
