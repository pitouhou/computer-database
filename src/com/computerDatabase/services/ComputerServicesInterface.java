package com.computerDatabase.services;

import java.util.Collection;
import com.computerDatabase.model.Computer;

public interface ComputerServicesInterface {

	public Collection<Computer> getComputerList();
	public Computer getComputerDetails(long id);
	public void addComputer(Computer computer);
	public void updateComputer(Computer computer);
	public void deleteComputer(long id);
	
}
