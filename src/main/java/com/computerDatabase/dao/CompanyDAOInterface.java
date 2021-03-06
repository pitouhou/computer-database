package com.computerDatabase.dao;

import java.sql.SQLException;
import java.util.List;

import com.computerDatabase.entity.model.Company;
import com.computerDatabase.exceptions.DAOException;

public interface CompanyDAOInterface extends DAOInterface<Company> {

  /**
   * Method to get all companies .
   * @return List<Company>
   */
  List<Company> findAll();
}
