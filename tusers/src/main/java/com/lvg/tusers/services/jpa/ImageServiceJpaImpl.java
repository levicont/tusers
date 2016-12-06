package com.lvg.tusers.services.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lvg.tusers.models.Image;
import com.lvg.tusers.repositories.ImageRepository;
import com.lvg.tusers.services.ImageService;

@Repository
@Service("imageService")
@Transactional
public class ImageServiceJpaImpl implements ImageService{
	private static final Logger LOG = Logger.getLogger(ImageServiceJpaImpl.class);

	@Autowired
	private ImageRepository imageRepository;
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Image> findAll() {
		
		return Lists.newArrayList(imageRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Image findById(Long id) {
		
		return imageRepository.findOne(id);
	}

	@Override
	public Image save(Image image) {
		LOG.info("STARTING SAVE IMAGE");
		return imageRepository.save(image);
	}

	@Override
	public void delete(Image image) {
		LOG.info("STARTING DELETE IMAGE");
		imageRepository.delete(image);
		LOG.info("IMAGE WITH ID: "+ image.getId() + " SUCCESSFULLY DELETED");
		
	}
	
	
	

}
