package com.computerDatabase.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

  // public Connection connect = ConnectionManager.getInstance();

  /**
   * Method to get object by id .
   * @param id : id
   * @return object
   */
  Optional<T> findById(long id);

  /**
   * Method to get all object .
   * @return Collection<Object>
   */
  List<T> findAll();

  /**
   * Method to create an object .
   * @param obj : obj
   */
  void create(T obj);

  /**
   * Method to update an object .
   * @param obj : obj
   */
  void update(T obj);

  /**
   * Method to delete an object .
   * @param id : id
   */
  void delete(long id);

  /**
   * Method to get number of computer .
   * @return nbComputer
   */
  int count();

}
