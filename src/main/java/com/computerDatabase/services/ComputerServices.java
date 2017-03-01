package com.computerDatabase.services;

import java.util.List;
import java.util.Optional;

import com.computerDatabase.DAO.ComputerDAO;
import com.computerDatabase.model.Computer;

public enum ComputerServices implements ComputerServicesInterface {

  instance;

  public static ComputerServices getInstance() {

    return ComputerServices.instance;

  }

  /**
   * Constructor of ComputerServices class .
   */
  ComputerServices() {

  }

  @Override public List<Optional<Computer>> getComputerList() {

    ComputerDAO comp = ComputerDAO.getInstance();
    List<Optional<Computer>> comp1;
    comp1 = comp.findAll();
    System.out.println("hello");
    System.out.println(comp1.size());
    return comp1;
  }

  @Override public Optional<Computer> getComputerDetails(long id) {

    ComputerDAO comp = ComputerDAO.getInstance();
    Optional<Computer> comp1;
    try {
      comp1 = comp.findById(id);
    } catch (NullPointerException e) {
      System.out.println("L'identifiant ne correspond a aucun ordinateur");
      return Optional.empty();
    }
    return comp1;
  }

  @Override public void addComputer(Computer computer) {

    ComputerDAO comp = ComputerDAO.getInstance();
    try {
      comp.create(computer);
      System.out.println("Ordinateur ajouté avec succés");
    } catch (NullPointerException e) {
      System.out.println("Erreur lors de l'ajout de l'ordinateur");
    }
  }

  @Override public void updateComputer(Computer computer) {

    ComputerDAO comp = ComputerDAO.getInstance();

    try {
      comp.update(computer);
      System.out.println("Ordinateur modifié avec succés");
    } catch (NullPointerException e) {
      System.out.println("Erreur lors de la modification des details de l'ordinateur");
    }
  }

  @Override public void deleteComputer(long id) {

    ComputerDAO comp = ComputerDAO.getInstance();
    try {
      comp.delete(id);
      System.out.println("Ordinateur supprimé avec succés");
    } catch (NullPointerException e) {
      System.out.println("Erreur lors de la suppression de l'ordinateur");
    }
  }
}