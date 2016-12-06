package com.lvg.tusers.services;

import java.util.List;

import com.lvg.tusers.models.User;

public interface UserService{
	List<User> findAll();
	User findById(Long id);
	User save(User user);
	boolean isUserUnique(User user);
	void delete(User user);
	
}
