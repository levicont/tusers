package com.lvg.tusers.services.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lvg.tusers.models.Gallery;
import com.lvg.tusers.repositories.GalleryRepository;
import com.lvg.tusers.services.GalleryService;

@Repository
@Service("galleryService")
@Transactional
public class GalleryServiceJpaImpl implements GalleryService{
	private static final Logger LOG = Logger.getLogger(GalleryServiceJpaImpl.class);
	
	@Autowired
	private GalleryRepository galleryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Gallery> findAll() {
		
		return Lists.newArrayList(galleryRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Gallery findById(Long id) {

		return galleryRepository.findOne(id);
	}

	@Override
	public Gallery save(Gallery gallery) {
		LOG.info("STARTING SAVE GALLERY");
		return galleryRepository.save(gallery);
	}

	@Override
	public void delete(Gallery gallery) {
		LOG.info("STARTING DELETE GALLERY");
		galleryRepository.delete(gallery);
		LOG.info("GALLERY WITH ID: "+ gallery.getId() + " SUCCESSFULLY DELETED");
	}
	
	
}
