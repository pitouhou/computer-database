package com.computerDatabase.entity.dto;

import java.util.Objects;

public class CompanyDTO {

  private long id;
  private String name;

  public CompanyDTO(){
    this.setName("company");
  }
  
  public CompanyDTO(long id, String name){
    this.setId(id);
    this.setName(name);
  }
  
  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public void setId(long id) {
    this.id = id;
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
    CompanyDTO company = (CompanyDTO) o;

    return Objects.equals(id, company.id) && Objects.equals(name, company.name);
  }

  @Override 
  public int hashCode() {
    return Objects.hash(id, name);
  }

  /*@Override 
  public String toString() {
    return String.valueOf(this);
  }*/
}
