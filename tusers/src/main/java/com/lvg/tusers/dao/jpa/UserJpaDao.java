package com.lvg.tusers.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;

@Repository("userDao")
@Transactional
public class UserJpaDao implements UserDao{
	
	private static final String GET_ALL_USERS_SQL = "SELECT e FROM user e"; 
	
		
	
	private EntityManagerFactory emf;
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	@Override
	public List<User> getAll() {		
		EntityManager em = emf.createEntityManager();
		if (em == null){
			User user = new User();
			user.setName("TOM");
			List<User> result = new ArrayList<>();
			result.add(user);
			return result;
		}
		return em.createQuery(GET_ALL_USERS_SQL).getResultList();		
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
