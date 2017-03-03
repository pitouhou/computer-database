package com.computerDatabase.services;

import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Computer;

public interface ComputerServicesInterface {

  /**
   * Methode to get Computer list .
   * @return Optional<Collection<Computer>>
   */
  List<Computer> getComputerList();

  /**
   * Method to get Computer details .
   * @param id : id
   * @return Optional<Computer>
   */
  Optional<Computer> getComputerDetails(long id);

  /**
   * Method to create a new Computer .
   * @param computer : computer
   */
  void addComputer(Computer computer);

  /**
   * Method to update a Computer .
   * @param computer : computer
   */
  void updateComputer(Computer computer);

  /**
   * Method to delete a COmputer .
   * @param id : id
   */
  void deleteComputer(long id);
  
  public int countComputer();

}
