package com.lvg.tusers.services;

import java.util.List;

import com.lvg.tusers.models.Gallery;

public interface GalleryService{
	List<Gallery> findAll();
	Gallery findById(Long id);
	Gallery save(Gallery gallery);
	void delete(Gallery gallery);
}
