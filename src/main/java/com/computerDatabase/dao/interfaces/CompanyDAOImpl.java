package com.computerDatabase.dao.interfaces;

import java.util.List;

import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Company;

public interface CompanyDAOImpl extends DAO<Company> {

  /**
   * Method to get all companies .
   * @return List<Company>
   */
  List<Company> findAll() throws DAOException;
}
