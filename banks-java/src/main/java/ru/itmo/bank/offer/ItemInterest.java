package ru.itmo.bank.offer;

public class ItemInterest {

  private float from;
  private float interest;

  public ItemInterest() {
  }

  public ItemInterest(float from, float interest) {
    this.from = from;
    this.interest = interest;
  }

  public float getFrom() {
    return from;
  }

  public float getInterest() {
    return interest;
  }
}
