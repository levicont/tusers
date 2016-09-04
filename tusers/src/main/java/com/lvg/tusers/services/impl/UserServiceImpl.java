package com.lvg.tusers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Override
	public List<User> getAll() {
		
		return dao.getAll();
	}

	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public void add(User record) {
		dao.add(record);
		
	}

	@Override
	public void update(User record) {
		dao.update(record);		
		
	}

	@Override
	public void delete(User record) {
		dao.delete(record);
		
	}

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	
	
	

}
