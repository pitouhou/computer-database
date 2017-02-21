package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.computerDatabase.model.Computer;

public class ComputerMapper {

	public static Computer mapComputer( ResultSet resultSet ) throws SQLException {
		Computer computer = new Computer();
		computer.setId(resultSet.getInt("id"));
		computer.setName(resultSet.getString("name"));
		computer.setCompany_id( resultSet.getInt( "company_id" ) );
		computer.setIntroduced( resultSet.getDate( "introduced" ) );
		computer.setDiscontinued( resultSet.getDate( "discontinued" ) );
	    return computer;
	}
	
	public static Collection<Computer> mapListComputer( ResultSet resultSet ) throws SQLException {
		
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
	
}
