package com.lvg.tusers.dao.hibernate;

import java.util.List;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;

@Repository
@Transactional(readOnly = true)
public class UserDaoHibernateImpl extends GenericDaoHibernate implements UserDao{

	@Override
	@Transactional
	public List<User> getAll() {		
		return getSession().createQuery("from User").list();
	}

	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
