package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Computer;

import junit.framework.TestCase;

public class ComputerMapperTest extends TestCase {

private ResultSet resultSet;
  
  @Before
  public void setUp() {
    resultSet = Mockito.mock(ResultSet.class);
  }

  @After
  public void tearDown() {
          
  }
  
  @Test
  public void testComputeurMap() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(1);
    
    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(DateUtils.convertToSQLDate("1992-12-10"));
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(DateUtils.convertToSQLDate("1994-12-10"));
    Mockito.when(resultSet.getLong("company.id")).thenReturn(1l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("company_test");

    Optional<Computer> computerO = ComputerMapper.mapComputer(resultSet);
    assertTrue(computerO.isPresent());
    Computer computer = computerO.get();
    assertEquals(computer.getId(),1l);
    assertEquals(computer.getName(),"computer_test");
    assertTrue(computer.getIntroduced().isPresent());
    assertEquals(computer.getIntroduced().get(),DateUtils.convertDate("1992-12-10"));
    assertTrue(computer.getDiscontinued().isPresent());
    assertEquals(computer.getDiscontinued().get(),DateUtils.convertDate("1994-12-10"));
    assertTrue(computer.getCompany().isPresent());
    assertEquals(computer.getCompany().get().getId(),1l);
    assertEquals(computer.getCompany().get().getName().get(),"company_test");
  }
  
  
  
  @Test
  public void testListComputerMap() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(2);
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(DateUtils.convertToSQLDate("1992-12-10"));
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(DateUtils.convertToSQLDate("1994-12-10"));
    Mockito.when(resultSet.getLong("company.id")).thenReturn(1l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("company_test");

    List<Optional<Computer>> computerList = ComputerMapper.mapListComputer(resultSet);
    assertTrue(!computerList.isEmpty());
    assertEquals(computerList.size(),2);
    for (Optional<Computer> comp: computerList) {
      assertTrue(comp.isPresent());
      
      Computer computer = comp.get();
      assertEquals(computer.getId(),1l);
      assertEquals(computer.getName(),"computer_test");
      assertTrue(computer.getIntroduced().isPresent());
      assertEquals(computer.getIntroduced().get(),DateUtils.convertDate("1992-12-10"));
      assertTrue(computer.getDiscontinued().isPresent());
      assertEquals(computer.getDiscontinued().get(),DateUtils.convertDate("1994-12-10"));
      assertTrue(computer.getCompany().isPresent());
      assertEquals(computer.getCompany().get().getId(),1l);
      assertEquals(computer.getCompany().get().getName().get(),"company_test");
    }
  }
  
  @Test
  public void testComputeurMapWithDateNull() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(1);
    
    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
    Mockito.when(resultSet.getLong("company.id")).thenReturn(1l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("company_test");

    Optional<Computer> computerO = ComputerMapper.mapComputer(resultSet);
    assertTrue(computerO.isPresent());
    Computer computer = computerO.get();
    assertEquals(computer.getId(),1l);
    assertEquals(computer.getName(),"computer_test");
    assertFalse(computer.getIntroduced().isPresent());
    assertEquals(computer.getIntroduced(),Optional.empty());
    assertFalse(computer.getDiscontinued().isPresent());
    assertEquals(computer.getDiscontinued(),Optional.empty());
    assertTrue(computer.getCompany().isPresent());
    assertEquals(computer.getCompany().get().getId(),1l);
    assertEquals(computer.getCompany().get().getName().get(),"company_test");
  }
  
  @Test
  public void testListComputeurMapWithDateNull() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(2);
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
    Mockito.when(resultSet.getLong("company.id")).thenReturn(1l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("company_test");

    List<Optional<Computer>> computerList = ComputerMapper.mapListComputer(resultSet);
    assertTrue(!computerList.isEmpty());
    assertEquals(computerList.size(),2);
    for (Optional<Computer> comp: computerList) {
      assertTrue(comp.isPresent());
      
      Computer computer = comp.get();
      assertEquals(computer.getId(),1l);
      assertEquals(computer.getName(),"computer_test");
      assertFalse(computer.getIntroduced().isPresent());
      assertEquals(computer.getIntroduced(),Optional.empty());
      assertFalse(computer.getDiscontinued().isPresent());
      assertEquals(computer.getDiscontinued(),Optional.empty());
      assertTrue(computer.getCompany().isPresent());
      assertEquals(computer.getCompany().get().getId(),1l);
      assertEquals(computer.getCompany().get().getName().get(),"company_test");
    }
  }
  
  @Test
  public void testComputeurMapCompanyNull() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(1);
    
    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(DateUtils.convertToSQLDate("1992-12-10"));
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(DateUtils.convertToSQLDate("1994-12-10"));
    Mockito.when(resultSet.getLong("company.id")).thenReturn(0l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("null");

    Optional<Computer> computerO = ComputerMapper.mapComputer(resultSet);
    assertTrue(computerO.isPresent());
    Computer computer = computerO.get();
    assertEquals(computer.getId(),1l);
    assertEquals(computer.getName(),"computer_test");
    assertTrue(computer.getIntroduced().isPresent());
    assertEquals(computer.getIntroduced().get(),DateUtils.convertDate("1992-12-10"));
    assertTrue(computer.getDiscontinued().isPresent());
    assertEquals(computer.getDiscontinued().get(),DateUtils.convertDate("1994-12-10"));
    assertTrue(computer.getCompany().isPresent());
    assertEquals(computer.getCompany().get().getId(),0l);
    assertEquals(computer.getCompany().get().getName().get(),"null");
  }
  
  @Test
  public void testListComputerMapCompanyNull() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(2);
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(DateUtils.convertToSQLDate("1992-12-10"));
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(DateUtils.convertToSQLDate("1994-12-10"));
    Mockito.when(resultSet.getLong("company.id")).thenReturn(0l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("null");

    List<Optional<Computer>> computerList = ComputerMapper.mapListComputer(resultSet);
    assertTrue(!computerList.isEmpty());
    assertEquals(computerList.size(),2);
    for (Optional<Computer> comp: computerList) {
      assertTrue(comp.isPresent());
      
      Computer computer = comp.get();
      assertEquals(computer.getId(),1l);
      assertEquals(computer.getName(),"computer_test");
      assertTrue(computer.getIntroduced().isPresent());
      assertEquals(computer.getIntroduced().get(),DateUtils.convertDate("1992-12-10"));
      assertTrue(computer.getDiscontinued().isPresent());
      assertEquals(computer.getDiscontinued().get(),DateUtils.convertDate("1994-12-10"));
      assertTrue(computer.getCompany().isPresent());
      assertEquals(computer.getCompany().get().getId(),0l);
      assertEquals(computer.getCompany().get().getName().get(),"null");
    }
  }
  
  @Test
  public void testComputeurMapFullNull() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(1);
    
    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
    Mockito.when(resultSet.getLong("company.id")).thenReturn(0l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("null");

    Optional<Computer> computerO = ComputerMapper.mapComputer(resultSet);
    assertTrue(computerO.isPresent());
    Computer computer = computerO.get();
    assertEquals(computer.getId(),1l);
    assertEquals(computer.getName(),"computer_test");
    assertFalse(computer.getIntroduced().isPresent());
    assertEquals(computer.getIntroduced(),Optional.empty());
    assertFalse(computer.getDiscontinued().isPresent());
    assertEquals(computer.getDiscontinued(),Optional.empty());
    assertTrue(computer.getCompany().isPresent());
    assertEquals(computer.getCompany().get().getId(),0l);
    assertEquals(computer.getCompany().get().getName().get(),"null");
  }
  
  @Test
  public void testListComputerMapFullNull() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(2);
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

    Mockito.when(resultSet.getLong("id")).thenReturn(1l);
    Mockito.when(resultSet.getString("name")).thenReturn("computer_test");
    Mockito.when(resultSet.getDate("introduced")).thenReturn(null);
    Mockito.when(resultSet.getDate("discontinued")).thenReturn(null);
    Mockito.when(resultSet.getLong("company.id")).thenReturn(0l);
    Mockito.when(resultSet.getString("company.name")).thenReturn("null");

    List<Optional<Computer>> computerList = ComputerMapper.mapListComputer(resultSet);
    assertTrue(!computerList.isEmpty());
    assertEquals(computerList.size(),2);
    for (Optional<Computer> comp: computerList) {
      assertTrue(comp.isPresent());
      
      Computer computer = comp.get();
      assertEquals(computer.getId(),1l);
      assertEquals(computer.getName(),"computer_test");
      assertFalse(computer.getIntroduced().isPresent());
      assertEquals(computer.getIntroduced(),Optional.empty());
      assertFalse(computer.getDiscontinued().isPresent());
      assertEquals(computer.getDiscontinued(),Optional.empty());
      assertTrue(computer.getCompany().isPresent());
      assertEquals(computer.getCompany().get().getId(),0l);
      assertEquals(computer.getCompany().get().getName().get(),"null");
    }
  }
}
