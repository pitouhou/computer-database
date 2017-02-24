package com.computerDatabase.DAO;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

	public Connection connect = ConnectionManager.getInstance();
	
	public abstract Optional<T> findById(long id);
	
	public abstract Optional<Collection<T>> findAll();
	
	public abstract void create(T obj);
	
	public abstract void update(T obj);
	
	public abstract void delete(long id);
	
}
