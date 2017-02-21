package com.computerDatabase.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.computerDatabase.model.Company;

import static com.computerDatabase.mapper.CompanyMapper.*;

import static com.computerDatabase.DAO.DAOUtils.*;

public class CompanyDaoImpl implements CompanyDao {

	private DAOFactory daoFactory;
	
	private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM company WHERE id = ?";
	
	CompanyDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Collection<Company> list() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Collection<Company> listCompany = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_FIND_ALL_COMPANY, false);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				listCompany = mapListCompany(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return listCompany;
	}
	
	@Override
	public Company getCompanyById(int id) throws DAOException {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_FIND_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				company = mapCompany(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return company;
		
	}

}
