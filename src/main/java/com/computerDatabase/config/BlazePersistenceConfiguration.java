package com.computerDatabase.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;

@Configuration
public class BlazePersistenceConfiguration {
  
  @PersistenceUnit(name = "cdb") 
  private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("cdb");

  @Bean 
  public EntityManager getEntityManager() {
    return entityManagerFactory.createEntityManager();
  }
}
