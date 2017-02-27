package com.computerDatabase.model;

import java.util.Objects;

public class Company {

  private long id;
  private String name;

  /**
   * Constructor of Company class .
   */
  public Company() {

  }

  /**
   * Constructor of Company class .
   * @param name : name
   */
  public Company(String name) {
    super();
    this.name = name;
  }

  public long getId() {
    return id;
  }

  /**
   * @param id : id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Return name.
   * @return string name
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public boolean equals(Object o) {

    if (this == o) {
      return true;
    }

    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    Company company = (Company) o;

    return Objects.equals(id, company.id) && Objects.equals(name, company.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override public String toString() {
    return String.valueOf(this);
  }

}
