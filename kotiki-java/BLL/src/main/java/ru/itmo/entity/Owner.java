package ru.itmo.entity;

import java.util.Date;
import java.util.List;

public interface Owner {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  Date getDateOfBirth();

  void setDateOfBirth(Date dateOfBirth);

  List<Cat> getCats();

  void setCats(List<Cat> cats);
}
