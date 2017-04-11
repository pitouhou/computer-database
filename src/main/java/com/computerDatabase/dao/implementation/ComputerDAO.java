package com.computerDatabase.dao.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;

import com.computerDatabase.core.model.Company;
import com.computerDatabase.core.model.Computer;
import com.computerDatabase.dao.ComputerDAOInterface;

@Component
public class ComputerDAO implements ComputerDAOInterface {

  @Autowired
  EntityManager em;
  
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ComputerDAO.class);

  /**
   * Constructor of ComputerDAO class .
   */
  private ComputerDAO() { }

  @Override public Optional<Computer> findById(long id) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Computer> criteria = builder.createQuery( Computer.class );
    Root<Computer> root = criteria.from( Computer.class );
    criteria.select( root );
    criteria.where( builder.equal( root.get( "id" ), id ) );

    Computer computer = em.createQuery( criteria ).getSingleResult();
    return Optional.ofNullable(computer);
  }

  @Override public void create(Computer computer) {
      if(!em.getTransaction().isActive()) em.getTransaction().begin();
      System.out.println("///////////////////////////////////////");
      System.out.println("///////////////////////////////////////");
      System.out.println(computer.getId());
      em.persist(computer);
      em.refresh(computer);
      em.getTransaction().commit();
  }

  @Override public void update(Computer computer) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    // create update
    CriteriaUpdate<Computer> update = builder.createCriteriaUpdate(Computer.class);

    // set the root class
    Root<Computer> root = update.from(Computer.class);

    Company companyId;
    long id = computer.getId();
    String name = computer.getName();
    LocalDate introduced;
    LocalDate discontinued;

    if(computer.getIntroduced()!=null){
      introduced = computer.getIntroduced();
    }else{
      introduced = null;
    }

    if(computer.getDiscontinued()!=null){
      discontinued = computer.getDiscontinued();
    }else{
      discontinued = null;
    }

    if(computer.getCompany()!=null){
      companyId = computer.getCompany();
    }else{
      companyId = null;
    }
    update.set("name", name);
    update.set("introduced", introduced);
    update.set("discontinued", discontinued);
    update.set("company", companyId);
    update.where(builder.equal( root.get( "id" ), computer.getId() ));
    if(!em.getTransaction().isActive()) em.getTransaction().begin();
    em.createQuery(update).executeUpdate();
    em.getTransaction().commit();
  }

  @Override public void delete(long id) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaDelete<Computer> delete = builder.createCriteriaDelete(Computer.class);

    Root<Computer> root = delete.from(Computer.class);

    delete.where(builder.equal( root.get( "id" ), id ));
    if(!em.getTransaction().isActive()) em.getTransaction().begin();
    em.createQuery(delete).executeUpdate();
    em.getTransaction().commit();
  }

  @Override 
  public int count() {
    CriteriaBuilder qb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    cq.select(qb.count(cq.from(Computer.class)));
    try{
      
      int count = em.createQuery(cq).getSingleResult().intValue();
      return count;
    }catch(NullPointerException e){
      return 0;
    }
    
  }

  @Override public List<Computer> findAll(int current, int range) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Computer> criteria = builder.createQuery( Computer.class );
    Root<Computer> root = criteria.from( Computer.class );
    criteria.select( root );
    try{
      
      List<Computer> list = em.createQuery( criteria ).setFirstResult((current*range)-range).setMaxResults(range).getResultList();
      
      return list;
    }catch(NullPointerException e){
      List<Computer> list = new ArrayList<>();
      return list;
    }
  }

  @Override public List<Computer> findByName(String name) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Computer> criteria = builder.createQuery( Computer.class );
    Root<Computer> root = criteria.from( Computer.class );
    criteria.select( root );
    criteria.where( builder.like(root.<String>get("name"), "%"+name+"%") );
    List<Computer> list = em.createQuery( criteria ).getResultList();
    return list;
  }
}
