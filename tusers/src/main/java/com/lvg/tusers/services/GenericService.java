package com.lvg.tusers.services;

import java.util.List;

public interface GenericService<T> {
	
	public List<T> getAll();
	
	public T getById(long id);
	
	public void add(T record);
	
	public void update(T record);
	
	public void delete(T record);
}
