package com.lvg.tusers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lvg.tusers.dao.GalleryDao;
import com.lvg.tusers.models.Gallery;
import com.lvg.tusers.services.GalleryService;

@Service("galleryService")
public class GalleryServiceImpl implements GalleryService {
	
	
	private GalleryDao dao;
	
	@Override
	public List<Gallery> getAll() {
		return dao.getAll();
	}

	@Override
	public Gallery getById(long id) {
		
		return dao.get(id);
	}

	@Override
	public void add(Gallery record) {
		dao.add(record);

	}

	@Override
	public void update(Gallery record) {
		dao.update(record);

	}

	@Override
	public void delete(Gallery record) {
		dao.delete(record);

	}

	public GalleryDao getDao() {
		return dao;
	}

	@Autowired
	public void setDao(GalleryDao dao) {
		this.dao = dao;
	}
	
	

}
