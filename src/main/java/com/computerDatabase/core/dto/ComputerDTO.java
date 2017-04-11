package com.computerDatabase.core.dto;

import java.util.Objects;

public class ComputerDTO {
  private long id;
  private String name;
  private String introduced;
  private String discontinued;
  private CompanyDTO company;
  
  public ComputerDTO(){
    
  }
  
  public ComputerDTO(long id, String name, String introduced, String discontinued, CompanyDTO company){
    this.setId(id);
    this.setName(name);
    this.setIntroduced(introduced);
    this.setDiscontinued(discontinued);
    this.setCompany(company);
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
  
  public void setId(long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public void setCompany(CompanyDTO company) {
    this.company = company;
  }

  @Override 
  public boolean equals(Object o) {

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
