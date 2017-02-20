package com.computerDatabase.DAO;

import java.util.Collection;

import com.computerDatabase.model.Company;

public interface CompanyDao {
	
	Collection<Company> list() throws DAOException;

}
