package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

public class ComputerMapper {

	public static Optional<Computer> mapComputer( ResultSet resultSet ) throws SQLException {
		Computer computer = new Computer();
		Company company = new Company();
		company.setId(resultSet.getLong("company.id"));
		company.setName(resultSet.getString("company.name"));
		computer.setId(resultSet.getLong("id"));
		computer.setName(resultSet.getString("name"));
		computer.setCompany(company);
		computer.setIntroduced( resultSet.getDate( "introduced" ) );
		computer.setDiscontinued( resultSet.getDate( "discontinued" ) );
	    return Optional.of(computer);
	}
	
	public static Optional<Collection<Computer>> mapListComputer( ResultSet resultSet ) throws SQLException {
		
		Collection<Computer> computerList = new ArrayList<Computer>();
		while(resultSet.next()){
			Computer computer = new Computer();
			Company company = new Company();
			company.setId(resultSet.getLong("company.id"));
			company.setName(resultSet.getString("company.name"));
			computer.setId(resultSet.getLong("id"));
			computer.setName(resultSet.getString("name"));
			computer.setCompany(company);
			computer.setIntroduced( resultSet.getDate( "introduced" ) );
			computer.setDiscontinued( resultSet.getDate( "discontinued" ) );
			computerList.add(computer);
		}
		
	    return Optional.of(computerList);
	}
	
}
