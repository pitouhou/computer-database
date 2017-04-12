package com.computerDatabase.dao.implementation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.computerDatabase.core.model.Company;
import com.computerDatabase.dao.CompanyDAOInterface;

@Component
public class CompanyDAO implements CompanyDAOInterface {

  private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
  private static final String SQL_FIND_BY_ID = "SELECT * FROM company WHERE id = ?";

  @Autowired
  EntityManager em;
  
  
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(CompanyDAO.class);

  /**
   * Constructor of CompanyDAO class .
   */
  private CompanyDAO() { }

  @Override
  public int count(){
    return 1;
  }
  
  @Override 
  public Optional<Company> findById(long id){
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Company> criteria = builder.createQuery( Company.class );
    Root<Company> root = criteria.from( Company.class );
    criteria.select( root );
    criteria.where( builder.equal( root.get( "id" ), id ) );

    Company company = em.createQuery( criteria ).getSingleResult();
    return Optional.ofNullable(company);
    
  }

  @Override 
  public List<Company> findAll(){

    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Company> criteria = builder.createQuery( Company.class );
    Root<Company> root = criteria.from( Company.class );
    criteria.select( root );
    List<Company> list = em.createQuery( criteria ).getResultList();

    return list;
  }

  @Override 
  public void create(Company obj) {

  }

  @Override 
  public void update(Company obj) {

  }

  @Override 
  public void delete(long id) {

  }
}
