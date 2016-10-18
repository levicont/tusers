package com.lvg.tusers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	
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
	
	

	@Override
	public boolean isUserUnique(User user) {
		List<User> list = getAll();
		String checkedEmail = user.getEmail();
		for(User u : list){
			if (u.getEmail().equalsIgnoreCase(checkedEmail))
				return false;
		}
		return true;
	}

	public UserDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	
	
	

}
