package com.computerDatabase.DAO;

import static com.computerDatabase.DAO.DAOUtils.silentCloses;
import static com.computerDatabase.DAO.DAOUtils.initPreparedStatement;
import static com.computerDatabase.mapper.CompanyMapper.mapCompany;
import static com.computerDatabase.mapper.CompanyMapper.mapListCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.computerDatabase.model.Company;

public class CompanyDAO implements DAO<Company>{

	private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
	private static final String SQL_FIND_BY_ID = "SELECT * FROM company WHERE id = ?";
	
	private CompanyDAO()
	{}
 
	private static class CompanyDAOHolder
	{	
		private final static CompanyDAO instance = new CompanyDAO();
	}
 
	public static CompanyDAO getInstance()
	{
		return CompanyDAOHolder.instance;
	}
	
	@Override
	public Company findById(long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_FIND_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				company = mapCompany(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(resultSet, preparedStatement, connexion);
		}
		
		return company;
	}

	@Override
	public Collection<Company> findAll() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Collection<Company> listCompany = null;
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPANY, false);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				listCompany = mapListCompany(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(resultSet, preparedStatement, connexion);
		}
		
		return listCompany;
	}

	@Override
	public void create(Company obj) {
		
		
	}

	@Override
	public void update(Company obj) {
		
		
	}

	@Override
	public void delete(long id) {
		
		
	}
}
