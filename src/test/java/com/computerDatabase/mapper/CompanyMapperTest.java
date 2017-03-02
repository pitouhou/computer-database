package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.computerDatabase.model.Company;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class CompanyMapperTest extends TestCase {

  private ResultSet resultSet;

  @Before
  public void setUp() {
    resultSet = Mockito.mock(ResultSet.class);
  }

  @After
  public void tearDown() {

  }

  @Test
  public void testCompanyMap() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(1);

    Mockito.when(resultSet.getLong("id")).thenReturn(1L);
    Mockito.when(resultSet.getString("name")).thenReturn("company_test");

    Optional<Company> companyO = CompanyMapper.mapCompany(resultSet);
    assertTrue(companyO.isPresent());
    Company company = companyO.get();
    assertEquals(company.getId(), 1L);
    assertTrue(company.getName().isPresent());
    assertEquals(company.getName().get(), "company_test");
  }

  @Test
  public void testListCompanyMap() throws SQLException {
    Mockito.when(resultSet.getRow()).thenReturn(2);
    Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

    Mockito.when(resultSet.getLong("id")).thenReturn(1L);
    Mockito.when(resultSet.getString("name")).thenReturn("company_test");

    List<Optional<Company>> companyList = CompanyMapper.mapListCompany(resultSet);
    assertTrue(!companyList.isEmpty());
    //Collection<Company> companyList = companyListO.get();
    assertEquals(companyList.size(), 2);
    for (Optional<Company> cO: companyList) {
        assertTrue(cO.isPresent());
        Company company = cO.get();
        assertEquals(company.getId(), 1L);
        assertTrue(company.getName().isPresent());
        assertEquals(company.getName().get(), "company_test");
    }
  }
}
