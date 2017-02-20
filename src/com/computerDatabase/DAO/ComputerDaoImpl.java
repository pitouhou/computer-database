package com.computerDatabase.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;


import com.computerDatabase.model.Computer;

public class ComputerDaoImpl implements ComputerDao {

	private DAOFactory daoFactory;
	
	ComputerDaoImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}
	
	private static Computer map( ResultSet resultSet ) throws SQLException {
		Computer computer = new Computer();
		computer.setId(resultSet.getInt("id"));
		computer.setName(resultSet.getString("name"));
		computer.setCompany_id( resultSet.getInt( "company_id" ) );
		computer.setIntroduced( resultSet.getDate( "introduced" ) );
		computer.setDiscontinued( resultSet.getDate( "discontinued" ) );
	    return computer;
	}
	
	private static Collection<Computer> mapList( ResultSet resultSet ) throws SQLException {
		
		Collection<Computer> computerList = new ArrayList<Computer>();
		while(resultSet.next()){
			Computer computer = new Computer();
			computer.setId(resultSet.getInt("id"));
			computer.setName(resultSet.getString("name"));
			computer.setCompany_id( resultSet.getInt( "company_id" ) );
			computer.setIntroduced( resultSet.getDate( "introduced" ) );
			computer.setDiscontinued( resultSet.getDate( "discontinued" ) );
			computerList.add(computer);
		}
		
	    return computerList;
	}
	
	public static void fermetureSilencieuse( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	public static void fermetureSilencieuse( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
	        }
	    }
	}

	public static void fermetureSilencieuse( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
	        }
	    }
	}

	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}

	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
	    fermetureSilencieuse( resultSet );
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}
	
	private static final String SQL_FIND_BY_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM computer";
	private static final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES = ?";
	
	@Override
	public void create(Computer computer) throws DAOException {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_CREATE_COMPUTER, false, (computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany_id()));
			resultSet = preparedStatement.executeQuery();
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		

	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Computer computer) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Computer> list() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Collection<Computer> listComputer = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_FIND_ALL, false);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				listComputer = mapList(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return listComputer;
	}

	@Override
	public Computer details(int id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Computer computer = null;
		
		try{
			
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, SQL_FIND_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				computer = map(resultSet);
			}
			
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return computer;
	}

}
