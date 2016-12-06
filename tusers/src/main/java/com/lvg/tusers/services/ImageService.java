package com.lvg.tusers.services;

import java.util.List;

import com.lvg.tusers.models.Image;

public interface ImageService{
	List<Image> findAll();
	Image findById(Long id);
	Image save(Image image);
	void delete(Image image);
}
