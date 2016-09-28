package com.lvg.tusers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lvg.tusers.dao.ImageDao;
import com.lvg.tusers.models.Image;
import com.lvg.tusers.services.ImageService;

public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao dao;
	
	@Override
	public List<Image> getAll() {
		return dao.getAll();
	}

	@Override
	public Image getById(long id) {
		
		return dao.get(id);
	}

	@Override
	public void add(Image record) {
		dao.add(record);

	}

	@Override
	public void update(Image record) {
		dao.update(record);

	}

	@Override
	public void delete(Image record) {
		dao.delete(record);

	}

	public ImageDao getDao() {
		return dao;
	}

	public void setDao(ImageDao dao) {
		this.dao = dao;
	}
	
	

}
