package ru.itmo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ru.itmo.accessory.Color;

@Entity
@Table(name = "cats")
public class CatDb {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cat_id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "date_of_birth")
  private Date dateOfBirth;

  @Column(name = "breed")
  private String breed;

  @Column(name = "color")
  @Enumerated(EnumType.STRING)
  private Color color;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id")
  private OwnerDb owner;

  public CatDb(String name, Date dateOfBirth, String breed, Color color) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    this.breed = breed;
    this.color = color;
  }

  public CatDb() {
  }

  public int getId() {
    return id;
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

  public OwnerDb getOwner() {
    return owner;
  }

  public void setOwner(OwnerDb owner) {
    this.owner = owner;
  }

  @Override
  public String toString() {
    return "id=" + id + ", name='" + name + ", birth date=" + dateOfBirth.getTime() + " breed="
        + breed + "color=" + color + "owner=" + owner.getId();
  }
}
