package com.computerDatabase.services;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Computer;

public interface ComputerServicesInterface {

	public Optional<Collection<Computer>> getComputerList();
	public Optional<Computer> getComputerDetails(long id);
	public void addComputer(Computer computer);
	public void updateComputer(Computer computer);
	public void deleteComputer(long id);
	
}
