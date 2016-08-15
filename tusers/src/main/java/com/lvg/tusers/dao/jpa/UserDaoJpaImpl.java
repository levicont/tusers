package com.lvg.tusers.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;

@Repository
public class UserDaoJpaImpl implements UserDao{
	
	private static final String GET_ALL_USERS_SQL = "SELECT u FROM user u"; 
	
		
	@PersistenceUnit(unitName="emf")
	private EntityManagerFactory emf;
	
	public EntityManagerFactory getEmf(){
		return this.emf;
	}
	
	public void setEmf(EntityManagerFactory entityManager){
		this.emf = entityManager;
	}
	
	
	@Override
	@Transactional
	public List<User> getAll() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(GET_ALL_USERS_SQL);
		if (emf == null){
			User user = new User();
			user.setName("TOM");
			List<User> result = new ArrayList<>();
			result.add(user);
			return result;
		}
		return query.getResultList();		
	}
	
	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
