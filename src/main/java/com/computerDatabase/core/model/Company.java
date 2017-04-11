package com.computerDatabase.core.model;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

  @Id
  private long id;
  
  @Column(name = "name")
  private String name;

  public Company(){
    
  }
  
  public Company(long id, String name){
    this.id = id;
    this.name = name;
  }
  
  /**
   * Builder of Company class .
   * @param builder : builder
   */
  

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public void setId(long id){
    this.id = id;
  }
  
  public void setName(String name){
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
