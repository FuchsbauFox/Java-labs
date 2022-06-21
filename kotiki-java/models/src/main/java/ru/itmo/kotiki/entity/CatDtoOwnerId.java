package ru.itmo.kotiki.entity;

import ru.itmo.kotiki.dto.CatDto;

public class CatDtoOwnerId {
  private final CatDto catDto;
  private final int ownerId;

  public CatDtoOwnerId(CatDto catDto, int ownerId) {
    this.catDto = catDto;
    this.ownerId = ownerId;
  }

  public CatDto getCatDto() {
    return catDto;
  }

  public int getOwnerId() {
    return ownerId;
  }
}
