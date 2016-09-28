package com.lvg.tusers.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lvg.tusers.dao.ImageDao;
import com.lvg.tusers.models.Image;

@Repository
@Transactional(readOnly = true)
public class ImageDaoHibernateImpl extends GenericDaoHibernate implements ImageDao {

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> getAll() {
		return getSession().createQuery("from Image").list();
	}

	@Override
	@Transactional
	public Image get(long id) {
		return (Image)getSession().get(Image.class, id);
	}

	@Override
	@Transactional
	public void add(Image record) {
		getSession().save(record);

	}

	@Override
	@Transactional
	public void update(Image record) {
		getSession().update(record);

	}

	@Override
	@Transactional
	public void delete(Image record) {
		getSession().delete(record);

	}

}
