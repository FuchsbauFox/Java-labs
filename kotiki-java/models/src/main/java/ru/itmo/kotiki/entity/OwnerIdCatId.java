package ru.itmo.kotiki.entity;

public class OwnerIdCatId {
  private final int ownerId;
  private final int catId;

  public OwnerIdCatId(int ownerId, int catId) {
    this.ownerId = ownerId;
    this.catId = catId;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public int getCatId() {
    return catId;
  }
}
