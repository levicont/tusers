package com.lvg.tusers.dao.hibernate;

import java.util.List;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;

@Repository("userDao")
@Transactional(readOnly = true)
public class UserDaoHibernateImpl extends GenericDaoHibernate implements UserDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAll() {		
		return getSession().createQuery("from User").list();
	}

	@Override
	public User get(long id) {
		
		return (User)getSession().get(User.class, id);
	}

	@Override
	@Transactional
	public void add(User record) {
		getSession().save(record);
		
	}

	@Override
	@Transactional
	public void update(User record) {
		getSession().update(record);
			
	}

	@Override
	@Transactional
	public void delete(User record) {
		getSession().delete(record);
		
	}
	
	
	
}
