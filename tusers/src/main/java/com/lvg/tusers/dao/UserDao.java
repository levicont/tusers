package com.lvg.tusers.dao;

import java.util.List;

import com.lvg.tusers.models.User;

public interface UserDao {
	
	public List<User> getAll();	
	public User get(long id);
	
}
