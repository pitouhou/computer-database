package com.computerDatabase.dao;

import java.util.List;

import com.computerDatabase.entity.model.Computer;

public interface ComputerDAOInterface extends DAOInterface<Computer> {

  /**
   * Method to get all object .
   * @return Collection<Object>
   */
  List<Computer> findAll(int current, int range);
  
  /**
   * Method to get computer by name .
   * @param name
   * @return List<Computer>
   */
  List<Computer> findByName(String name);
}
