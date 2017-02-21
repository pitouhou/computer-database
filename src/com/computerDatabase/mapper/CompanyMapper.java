package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.computerDatabase.model.Company;

public class CompanyMapper {

	public static Collection<Company> mapListCompany( ResultSet resultSet ) throws SQLException {
		
		Collection<Company> companyList = new ArrayList<Company>();
		while(resultSet.next()){
			Company company = new Company();
			company.setId(resultSet.getInt("id"));
			company.setName(resultSet.getString("name"));
			companyList.add(company);
		}
		
	    return companyList;
	}
	
}
