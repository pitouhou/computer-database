package com.computerDatabase.dto;

import java.util.Objects;

public class CompanyDTO {

  private final long id;
  private final String name;

  /**
   * Builder of CompanyDTO class .
   * @param builder : builder
   */
  private CompanyDTO(CompanyDTOBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static class CompanyDTOBuilder {
    private final long id;
    private final String name;

    /**
     * Method to build CompanyDTO object .
     * @param id : id
     * @param name : name
     */
    public CompanyDTOBuilder(long id, String name) {
      this.id = id;
      this.name = name;
    }

    /**
     * Method to build CompanyDTO object .
     * @return new instance of Company
     */
    public CompanyDTO build() {
      return new CompanyDTO(this);
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
    CompanyDTO company = (CompanyDTO) o;

    return Objects.equals(id, company.id) && Objects.equals(name, company.name);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override public String toString() {
    return String.valueOf(this);
  }
}
