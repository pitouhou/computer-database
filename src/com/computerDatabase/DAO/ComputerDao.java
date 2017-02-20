package com.computerDatabase.DAO;

import java.util.Collection;

import com.computerDatabase.model.*;

public interface ComputerDao {
	
    void create( Computer computer ) throws DAOException;
    void delete(int id) throws DAOException;
    void update( Computer computer ) throws DAOException;
    Collection<Computer> list() throws DAOException;
    Computer details( int id ) throws DAOException;
	
}
