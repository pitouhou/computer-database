package com.computerDatabase.DAO;

import java.util.Collection;

import com.computerDatabase.model.*;

public interface ComputerDao {
	
    void create( Computer computer ) throws DAOException;
    void delete(long id) throws DAOException;
    void update( Computer computer ) throws DAOException;
    Collection<Computer> list() throws DAOException;
    Computer details( long id ) throws DAOException;
	
}
