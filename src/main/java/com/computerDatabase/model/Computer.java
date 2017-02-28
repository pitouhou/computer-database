package com.computerDatabase.model;

import java.time.LocalDate;
import java.util.Objects;

public class Computer {
  private final long id;
  private final String name;
  private final LocalDate introduced;
  private final LocalDate discontinued;
  private final Company company;

  private Computer(ComputerBuilder builder){
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
  
  public LocalDate getIntroduced() {
    return introduced;
  }
  
  public LocalDate getDiscontinued() {
    return discontinued;
  }
  
  public Company getCompany() {
    return company;
  }

  public static class ComputerBuilder {
    private long id;
    private final String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    public ComputerBuilder(String name) {
      this.name = name;
    }

    public ComputerBuilder id(long id){
      this.id = id;
      return this;
    }
    
    public ComputerBuilder introduced(LocalDate introduced) {
      this.introduced = introduced;
      return this;
    }

    public ComputerBuilder discontinued(LocalDate discontinued) {
      this.discontinued = discontinued;
      return this;
    }

    public ComputerBuilder company(Company company) {
      this.company = company;
      return this;
    }

    public Computer build() {
      return new Computer(this);
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
    Computer computer = (Computer) o;

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
