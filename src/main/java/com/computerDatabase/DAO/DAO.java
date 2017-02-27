package com.computerDatabase.DAO;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

  // public Connection connect = ConnectionManager.getInstance();

  Optional<T> findById(long id);

  Optional<Collection<T>> findAll();

  void create(T obj);

  void update(T obj);

  void delete(long id);

}
