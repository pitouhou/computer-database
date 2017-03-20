package com.computerDatabase.dao.interfaces;

import java.util.List;

import com.computerDatabase.model.Computer;

public interface ComputerDAOImpl extends DAO<Computer> {

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
