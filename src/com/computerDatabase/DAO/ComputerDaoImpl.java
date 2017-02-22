package com.computerDatabase.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


import com.computerDatabase.model.Computer;

import static com.computerDatabase.DAO.DAOUtils.*;

import static com.computerDatabase.mapper.ComputerMapper.*;

public class ComputerDaoImpl implements ComputerDao {

	private DAOFactory daoFactory;
	
	ComputerDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	private static final String SQL_FIND_BY_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private static final String SQL_FIND_ALL_COMPUTER = "SELECT * FROM computer";
	private static final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
	private static final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	
	
	@Override
	public void create(Computer computer) throws DAOException {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		String name = computer.getName();
		Date introduced = computer.getIntroduced();
		Date discontinued = computer.getDiscontinued();
		long company_id = computer.getCompany().getId();
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_CREATE_COMPUTER, false, name, introduced, discontinued, company_id);
			resultSet = preparedStatement.executeUpdate();
			//System.out.println(resultSet);
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(preparedStatement, connexion);
		}
		
	}

	@Override
	public void delete(long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE_COMPUTER, false, id);
			resultSet = preparedStatement.executeUpdate();
			System.out.println(resultSet);
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Computer computer) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		long id = computer.getId();
		String name = computer.getName();
		Date introduced = computer.getIntroduced();
		Date discontinued = computer.getDiscontinued();
		long company_id = computer.getCompany().getId();
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_COMPUTER, false, name, introduced, discontinued, company_id, id);
			resultSet = preparedStatement.executeUpdate();
			System.out.println(resultSet);
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public Collection<Computer> list() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Collection<Computer> listComputer = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_FIND_ALL_COMPUTER, false);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				listComputer = mapListComputer(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return listComputer;
	}

	@Override
	public Computer details(long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_FIND_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				computer = mapComputer(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return computer;
	}

}
