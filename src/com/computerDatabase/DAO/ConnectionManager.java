package com.computerDatabase.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ConnectionManager {

	private static Connection connect;
	
	public static Connection getInstance(){
		try {
			Optional<Properties> propertiesTmp = ConnectionManager.getProperties("DAO.properties");
			Properties properties = propertiesTmp.get();
			if(connect == null){
				try {
					connect = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
			return connect;	
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return connect;
	}
	
	public static Optional<Properties> getProperties(String fileName) throws FileNotFoundException, IOException{
		
		Properties properties = new Properties();
		File	file = new File(fileName);
		if(file.exists()){
			FileInputStream input = new FileInputStream(fileName); 
		    
		      try{

		         properties.load(input);
		         return Optional.of(properties);

		      }finally{

		         input.close();

		      }
		      
		}
		return Optional.empty();
	}
	
}
