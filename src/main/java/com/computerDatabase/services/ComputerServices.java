package com.computerDatabase.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.dao.ComputerDAO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.model.Computer;

public enum ComputerServices implements ComputerServicesInterface {

  instance;

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ComputerServices.class);

  public static ComputerServices getInstance() {

    return ComputerServices.instance;

  }

  /**
   * Constructor of ComputerServices class .
   */
  ComputerServices() {

  }
  
  public List<Computer> getComputerList(int current, int range) {

    ComputerDAO comp = ComputerDAO.getInstance();
    List<Computer> comp1;
    comp1 = comp.findAll(current, range);
    return comp1;
  }
  
  public List<Computer> getByName(String name) {

    ComputerDAO comp = ComputerDAO.getInstance();
    List<Computer> comp1;
    comp1 = comp.findByName(name);
    return comp1;
  }

  @Override
  public int countComputer(){
    ComputerDAO comp = ComputerDAO.getInstance();
    return comp.count();
  }

  @Override
  public Optional<Computer> getComputerDetails(long id) {

    ComputerDAO comp = ComputerDAO.getInstance();
    Optional<Computer> comp1;
    try {
      comp1 = comp.findById(id);
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on getting computer by id");
      return Optional.empty();
    }
    return comp1;
  }

  @Override
  public void addComputer(Computer computer) {

    ComputerDAO comp = ComputerDAO.getInstance();
    try {
      comp.create(computer);
      LOGGER.info("Success on adding computer");
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on adding computer");
    }
  }

  @Override
  public void updateComputer(Computer computer) {

    ComputerDAO comp = ComputerDAO.getInstance();

    try {
      comp.update(computer);
      LOGGER.info("Success on updating computer");
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on updating computer");
    }
  }

  @Override
  public void deleteComputer(long id) {

    ComputerDAO comp = ComputerDAO.getInstance();
    try {
      comp.delete(id);
      LOGGER.info("Success on deleting computer");
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on deleting computer");
    }
  }
}
