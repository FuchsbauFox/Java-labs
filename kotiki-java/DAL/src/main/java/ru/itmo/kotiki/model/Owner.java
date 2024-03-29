package ru.itmo.kotiki.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "owners")
public class Owner {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "owner_id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "date_of_birth")
  private Date dateOfBirth;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Cat> cats;

  @OneToOne(mappedBy = "owner")
  private User user;

  public Owner(String name, Date dateOfBirth) {
    this.name = name;
    this.dateOfBirth = dateOfBirth;
    cats = new ArrayList<Cat>();
  }

  public Owner() {
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Owner{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", user=" + user.getId() +
        '}';
  }
}
