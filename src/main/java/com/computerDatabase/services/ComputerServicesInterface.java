package com.computerDatabase.services;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Computer;

public interface ComputerServicesInterface {

  Optional<Collection<Computer>> getComputerList();

  Optional<Computer> getComputerDetails(long id);

  void addComputer(Computer computer);

  void updateComputer(Computer computer);

  void deleteComputer(long id);

}
