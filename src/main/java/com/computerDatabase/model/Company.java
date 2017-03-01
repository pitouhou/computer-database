package com.computerDatabase.model;

import java.util.Objects;
import java.util.Optional;

public class Company {

  private final long id;
  private final String name;

  /**
   * Builder of Company class .
   * @param builder : builder
   */
  private Company(CompanyBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  public long getId() {
    return id;
  }

  public Optional<String> getName() {
    return Optional.ofNullable(name);
  }

  public static class CompanyBuilder {
    private final long id;
    private final String name;

    /**
     * Method to build Company object .
     * @param id : id
     * @param name : name
     */
    public CompanyBuilder(long id, String name) {
      this.id = id;
      this.name = name;
    }

    /**
     * Method to build Company object .
     * @return new instance of Company
     */
    public Company build() {
      return new Company(this);
    }

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
