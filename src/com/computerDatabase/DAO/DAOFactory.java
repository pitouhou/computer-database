package com.computerDatabase.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	
	private static final String PROPERTY_URL	= "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String PROPERTY_DRIVER	= "com.mysql.jdbc.Driver";
	private static final String PROPERTY_NOM_UTILISATEUR	= "admincdb";
	private static final String PROPERTY_MOT_DE_PASSE	= "qwerty1234";
	
	private String url;
	private String username;
	private String password;
	
	DAOFactory(String url, String username, String password){
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		
		String url =  PROPERTY_URL ;
		String driver = PROPERTY_DRIVER ;
		String nomUtilisateur = PROPERTY_NOM_UTILISATEUR ;
		String motDePasse = PROPERTY_MOT_DE_PASSE ;
		
		
		
		
		
		try{
			Class.forName( driver );
		}catch(ClassNotFoundException e){
			throw new DAOConfigurationException( "Le driver est introuvable", e);
		}
		
		DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
		return instance;
	}
	
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public ComputerDao getComputerDao() {
        return new ComputerDaoImpl( this );
    }
	
}
