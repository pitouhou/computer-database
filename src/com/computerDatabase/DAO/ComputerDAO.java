package com.computerDatabase.DAO;

import static com.computerDatabase.DAO.DAOUtils.silentCloses;
import static com.computerDatabase.DAO.DAOUtils.initPreparedStatement;
import static com.computerDatabase.mapper.ComputerMapper.mapComputer;
import static com.computerDatabase.mapper.ComputerMapper.mapListComputer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import com.computerDatabase.model.Computer;

public class ComputerDAO implements DAO<Computer>{

	private static final String SQL_FIND_BY_ID = "SELECT * FROM computer RIGHT JOIN company ON computer.company_id = company.id WHERE computer.id = ?";
	private static final String SQL_FIND_ALL_COMPUTER = "SELECT * FROM computer RIGHT JOIN company ON computer.company_id = company.id";
	private static final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
	private static final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	
	private ComputerDAO()
	{}
 
	private static class ComputerDAOHolder
	{	
		private final static ComputerDAO instance = new ComputerDAO();
	}
 
	public static ComputerDAO getInstance()
	{
		return ComputerDAOHolder.instance;
	}
	
	@Override
	public Computer findById(long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_FIND_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				computer = mapComputer(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(resultSet, preparedStatement, connexion);
		}
		
		return computer;
	}

	@Override
	public Collection<Computer> findAll() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Collection<Computer> listComputer = null;
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPUTER, false);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				listComputer = mapListComputer(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(resultSet, preparedStatement, connexion);
		}
		
		return listComputer;
	}

	@Override
	public void create(Computer computer) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		String name = computer.getName();
		Date introduced = computer.getIntroduced();
		Date discontinued = computer.getDiscontinued();
		long company_id = computer.getCompany().getId();
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_CREATE_COMPUTER, false, name, introduced, discontinued, company_id);
			resultSet = preparedStatement.executeUpdate();
			System.out.println(resultSet);
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(preparedStatement, connexion);
		}
	}

	@Override
	public void update(Computer computer) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		long id = computer.getId();
		String name = computer.getName();
		Date introduced = computer.getIntroduced();
		Date discontinued = computer.getDiscontinued();
		long company_id = computer.getCompany().getId();
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_UPDATE_COMPUTER, false, name, introduced, discontinued, company_id, id);
			resultSet = preparedStatement.executeUpdate();
			System.out.println(resultSet);
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(preparedStatement, connexion);
		}
	}

	@Override
	public void delete(long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		int resultSet;
		
		try{
			
			connexion = DAO.connect;
			preparedStatement = initPreparedStatement(connexion, SQL_DELETE_COMPUTER, false, id);
			resultSet = preparedStatement.executeUpdate();
			System.out.println(resultSet);
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			silentCloses(preparedStatement, connexion);
		}
		
	}
	
	
	
	
}
