package com.lvg.tusers.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;

@Repository
public class UserDaoJpaImpl implements UserDao{
	
	private static final String GET_ALL_USERS_SQL = "SELECT u FROM user u"; 
			
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<User> getAll() {
		Session session = getSession();
		
		if (session == null){
			User user = new User();
			user.setName("TOM");
			List<User> result = new ArrayList<>();
			result.add(user);
			return result;
		}
		return session.createQuery(GET_ALL_USERS_SQL).list();		
	}
	
	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
