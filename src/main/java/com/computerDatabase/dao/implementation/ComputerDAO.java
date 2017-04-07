package com.computerDatabase.dao.implementation;

import static com.computerDatabase.dao.connection.DAOUtils.initPreparedStatement;
import static com.computerDatabase.dao.connection.DAOUtils.closeAll;
import static com.computerDatabase.dao.connection.DAOUtils.commit;
import static com.computerDatabase.dao.connection.DAOUtils.rollBack;
import static com.computerDatabase.entity.mapper.ComputerMapper.mapComputer;
import static com.computerDatabase.entity.mapper.ComputerMapper.mapListComputer;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.computerDatabase.dao.ComputerDAOInterface;
import com.computerDatabase.dao.connection.ConnectionManager;
import com.computerDatabase.entity.model.Company;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.exceptions.DAOException;

@Component
public class ComputerDAO implements ComputerDAOInterface {

  private static final String SQL_FIND_BY_ID = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ?";
  private static final String SQL_FIND_ALL_COMPUTER = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
  private static final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
  private static final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
  private static final String SQL_COUNT_COMPUTER = "SELECT COUNT(id) FROM computer";
  private static final String SQL_FIND_BY_NAME = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
  
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

  @Override public void create(Computer obj) {
    // TODO Auto-generated method stub
    
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
    
    if(computer.getIntroduced().isPresent()){
      introduced = computer.getIntroduced().get();
    }else{
      introduced = null;
    }
    
    if(computer.getDiscontinued().isPresent()){
      discontinued = computer.getDiscontinued().get();
    }else{
      discontinued = null;
    }
    
    if(computer.getCompany().isPresent()){
      companyId = computer.getCompany().get();
    }else{
      companyId = null;
    }
    // set update and where clause
    update.set("name", name);
    update.set("introduced", introduced);
    update.set("discontinued", discontinued);
    update.set("company", companyId);
    update.where(builder.equal( root.get( "id" ), computer.getId() ));
    em.getTransaction().begin();
    em.createQuery(update).executeUpdate();
    em.getTransaction().commit();
  }

  @Override public void delete(long id) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaDelete<Computer> delete = builder.createCriteriaDelete(Computer.class);

    Root<Computer> root = delete.from(Computer.class);

    delete.where(builder.equal( root.get( "id" ), id ));
    em.getTransaction().begin();
    em.createQuery(delete).executeUpdate();
    em.getTransaction().commit();
  }

  @Override public int count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override public List<Computer> findAll(int current, int range) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Computer> criteria = builder.createQuery( Computer.class );
    Root<Computer> root = criteria.from( Computer.class );
    criteria.select( root );

    List<Computer> list = em.createQuery( criteria ).setFirstResult((current*range)-range).setMaxResults(range).getResultList();
    return list;
  }

  @Override public List<Computer> findByName(String name) {
    CriteriaBuilder builder = em.getCriteriaBuilder();

    CriteriaQuery<Computer> criteria = builder.createQuery( Computer.class );
    Root<Computer> root = criteria.from( Computer.class );
    criteria.select( root );
    criteria.where( builder.like(root.<String>get("name"), "%"+name+"%") );
    List<Computer> list = em.createQuery( criteria ).getResultList();
    System.out.println(list.size());
    return list;
  }

//  @Override
//  public int count(){
//    int cb = cbf.create(em, Computer.class).getMaxResults();
//    return cb;
//  }
//
//  @Override
//  public Optional<Computer> findById(long id){
//    CriteriaBuilder<Computer> cb = cbf.create(em, Computer.class).where("computer.id").like().value(id).noEscape();
//    return Optional.of(cb.getSingleResult());
//  }
//  
//  @Override
//  public List<Computer> findAll(int current, int range) {
////    System.out.println("////////////////////////////////////////////////////");
////    System.out.println("////////////////////////////////////////////////////");
////    System.out.println("////////////////////////////////////////////////////");
////    
////    CriteriaBuilder<Computer> cb = cbf.create(em, Computer.class).from(Computer.class, "computer").orderByAsc("id").setFirstResult((current*range)-range).setMaxResults(range);
//    return null;
//  }
//  
//  @Override
//  public List<Computer> findByName(String name) {
//    CriteriaBuilder<Computer> cb = cbf.create(em, Computer.class).where("computer.name").like().value(name).noEscape();
//    return cb.getResultList();
//  }
//
//  @Override public void create(Computer computer) {
//    String companyId;
//    LocalDate introduced;
//    LocalDate discontinued;
//    String name = computer.getName();
//    if(computer.getIntroduced().isPresent()){
//      introduced = computer.getIntroduced().get();
//    }else{
//      introduced = null;
//    }
//    
//    if(computer.getDiscontinued().isPresent()){
//      discontinued = computer.getDiscontinued().get();
//    }else{
//      discontinued = null;
//    }
//    
//    if(computer.getCompany().isPresent()){
//      companyId = Long.toString(computer.getCompany().get().getId());
//    }else{
//      companyId = null;
//    }
//    
//  }
//
//  @Override public void update(Computer computer) {
//   
//    String companyId;
//    long id = computer.getId();
//    String name = computer.getName();
//    LocalDate introduced;
//    LocalDate discontinued;
//    
//    if(computer.getIntroduced().isPresent()){
//      introduced = computer.getIntroduced().get();
//    }else{
//      introduced = null;
//    }
//    
//    if(computer.getDiscontinued().isPresent()){
//      discontinued = computer.getDiscontinued().get();
//    }else{
//      discontinued = null;
//    }
//    
//    if(computer.getCompany().isPresent()){
//      companyId = Long.toString(computer.getCompany().get().getId());
//    }else{
//      companyId = null;
//    }
//    cbf.update(em, Computer.class, "computer").set("name", computer.getName()).set("introduced", computer.getIntroduced()).set("discontinued", computer.getDiscontinued()).set("company", computer.getCompany());
//  }
//
//  @Override public void delete(long id) {
//    cbf.delete(em, Computer.class, "computer").where("computer.id").like().value(id);
//  }

}
