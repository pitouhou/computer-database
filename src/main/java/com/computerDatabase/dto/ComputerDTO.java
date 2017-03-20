package com.computerDatabase.dto;

import java.util.Objects;

public class ComputerDTO {
  private final long id;
  private final String name;
  private final String introduced;
  private final String discontinued;
  private final CompanyDTO company;

  private ComputerDTO(ComputerDTOBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.introduced = builder.introduced;
    this.discontinued = builder.discontinued;
    this.company = builder.company;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public CompanyDTO getCompany() {
    return company;
  }

  public static class ComputerDTOBuilder {
    private long id;
    private final String name;
    private String introduced;
    private String discontinued;
    private CompanyDTO company;

    public ComputerDTOBuilder(String name) {
      this.name = name;
    }

    public ComputerDTOBuilder id(long id) {
      this.id = id;
      return this;
    }

    public ComputerDTOBuilder introduced(String introduced) {
      this.introduced = introduced;
      return this;
    }

    public ComputerDTOBuilder discontinued(String discontinued) {
      this.discontinued = discontinued;
      return this;
    }

    public ComputerDTOBuilder company(CompanyDTO company) {
      this.company = company;
      return this;
    }

    public ComputerDTO build() {
      return new ComputerDTO(this);
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
    ComputerDTO computer = (ComputerDTO) o;

    return Objects.equals(id, computer.id) && Objects.equals(name, computer.name)
        && Objects.equals(introduced, computer.introduced)
        && Objects.equals(discontinued, computer.discontinued)
        && Objects.equals(company, computer.company);
  }

  @Override public int hashCode() {
    return Objects.hash(id, name, introduced, discontinued, company);
  }

  @Override public String toString() {
    return String.valueOf(this);
  }
}
