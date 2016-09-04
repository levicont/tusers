package com.lvg.tusers.dao;

import java.util.List;

public interface GenericDao<T> {
	
	public List<T> getAll();
	
	public T get(long id);
	
	public void add(T record);
	
	public void update(T record);
	
	public void delete(T record);

}
