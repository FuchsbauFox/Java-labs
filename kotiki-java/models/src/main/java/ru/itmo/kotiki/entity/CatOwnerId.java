package ru.itmo.kotiki.entity;

import ru.itmo.kotiki.model.Cat;

public class CatOwnerId {
  private final Cat cat;
  private final int ownerId;

  public CatOwnerId(Cat cat, int ownerId) {
    this.cat = cat;
    this.ownerId = ownerId;
  }

  public Cat getCat() {
    return cat;
  }

  public int getOwnerId() {
    return ownerId;
  }
}
