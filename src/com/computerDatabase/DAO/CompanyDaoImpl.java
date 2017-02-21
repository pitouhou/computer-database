package com.computerDatabase.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

import static com.computerDatabase.DAO.DAOUtils.*;

public class CompanyDaoImpl implements CompanyDao {

	private DAOFactory daoFactory;
	
	private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
	
	CompanyDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	private static Collection<Company> mapListCompany( ResultSet resultSet ) throws SQLException {
		
		Collection<Company> companyList = new ArrayList<Company>();
		while(resultSet.next()){
			Company company = new Company();
			company.setId(resultSet.getInt("id"));
			company.setName(resultSet.getString("name"));
			companyList.add(company);
		}
		
	    return companyList;
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

}
