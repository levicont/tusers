package com.lvg.tusers.models;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileForm {
	
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
