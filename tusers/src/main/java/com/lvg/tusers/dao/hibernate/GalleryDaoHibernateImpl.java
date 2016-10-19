package com.lvg.tusers.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lvg.tusers.dao.GalleryDao;
import com.lvg.tusers.models.Gallery;

@Repository("galleryDao")
@Transactional(readOnly = true)
public class GalleryDaoHibernateImpl extends GenericDaoHibernate implements GalleryDao{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Gallery> getAll() {		
		return getSession().createQuery("from Gallery").list();
	}

	@Override
	@Transactional
	public Gallery get(long id) {		
		return (Gallery)getSession().get(Gallery.class, id);
	}

	@Override
	@Transactional
	public void add(Gallery record) {
		getSession().save(record);
		
	}

	@Override
	@Transactional
	public void update(Gallery record) {
		getSession().update(record);
		
	}

	@Override
	@Transactional
	public void delete(Gallery record) {
		getSession().delete(record);
		
	}
	
}
