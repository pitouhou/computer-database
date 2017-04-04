package com.computerDatabase.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.dao.implementation.ComputerDAO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.model.Computer;

@Component
public class ComputerServices implements ComputerServicesInterface {

  @Autowired
  private ComputerDAO computerDAO;
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ComputerServices.class);

  /**
   * Constructor of ComputerServices class .
   */
  ComputerServices() {

  }
  
  public List<Computer> getComputerList(int current, int range) {

    List<Computer> comp1;
    comp1 = computerDAO.findAll(current, range);
    return comp1;
  }
  
  public List<Computer> getByName(String name) {

    List<Computer> comp1;
    comp1 = computerDAO.findByName(name);
    return comp1;
  }

  @Override
  public int countComputer(){
    return computerDAO.count();
  }

  @Override
  public Optional<Computer> getComputerDetails(long id) {

    Optional<Computer> comp1;
    try {
      comp1 = computerDAO.findById(id);
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on getting computer by id");
      return Optional.empty();
    }
    return comp1;
  }

  @Override
  public void addComputer(Computer computer) {
    
    try {
      computerDAO.create(computer);
      LOGGER.info("Success on adding computer");
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on adding computer");
    }
  }

  @Override
  public void updateComputer(Computer computer) {

    try {
      computerDAO.update(computer);
      LOGGER.info("Success on updating computer");
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on updating computer");
    }
  }

  @Override
  public void deleteComputer(long id) {

    try {
      computerDAO.delete(id);
      LOGGER.info("Success on deleting computer");
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on deleting computer");
    }
  }
}
