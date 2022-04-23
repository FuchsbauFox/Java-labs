package ru.itmo.entity;

import java.util.Date;
import ru.itmo.accessory.Color;

public interface Cat {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  Date getDateOfBirth();

  void setDateOfBirth(Date dateOfBirth);

  String getBreed();

  void setBreed(String breed);

  Color getColor();

  void setColor(Color color);

  int getOwnerId();

  void setOwnerId(int ownerId);
}
