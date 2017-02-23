package com.computerDatabase.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static Connection connect;
	
	public static Connection getInstance(){
		if(connect == null){
			try {
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull", "admincdb", "qwerty1234");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return connect;	
	}
	
}
