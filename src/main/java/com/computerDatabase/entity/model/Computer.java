package com.computerDatabase.entity.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class Computer {
  
  @Id
  private long id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "introduced")
  private LocalDate introduced;
  
  @Column(name = "discontinued")
  private LocalDate discontinued;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private Company company;
  
  public Computer(){
    
  }
  
public Computer(String name, LocalDate introduced, LocalDate discontinued, Company company){
    
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
    
  }
  
  public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, Company company){
    
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
    
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Optional<LocalDate> getIntroduced() {
    return Optional.ofNullable(introduced);
  }

  public Optional<LocalDate> getDiscontinued() {
    return Optional.ofNullable(discontinued);
  }

  public Optional<Company> getCompany() {
    return Optional.ofNullable(company);
  }
  
  public void setId(long id){
    this.id = id;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public void setIntroduced(LocalDate introduced){
    this.introduced = introduced;
  }
  
  public void setDisconinued(LocalDate discontinued){
    this.discontinued = discontinued;
  }
  
  public void setCompany(Company company){
    this.company = company;
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
