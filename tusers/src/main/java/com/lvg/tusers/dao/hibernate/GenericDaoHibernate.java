package com.lvg.tusers.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoHibernate {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
