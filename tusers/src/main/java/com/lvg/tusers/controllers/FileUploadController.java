package com.lvg.tusers.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lvg.tusers.models.UploadFileForm;

@Controller
public class FileUploadController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	private String uploadFile(Model model, @ModelAttribute("uploadFileForm") UploadFileForm uploadFileForm)
			throws Exception {
		if(uploadFileForm == null){
			System.out.println("UPLOAD_FILE_FORM IS NULL ");
			return "home";
		}
		
		MultipartFile file = uploadFileForm.getFile();
		
		if(file == null){
			System.out.println("FILE IS NULL ");
			return "home";
		}
		if (!file.isEmpty()) {
			System.out.println("FILE HAS UPLOADED : " + file.getOriginalFilename());
//			byte[] mainImageSrc = file.getBytes();
//			model.addAttribute("mainImage", mainImageSrc);

		}else{
			System.out.println("FILE HAS NOT UPLOADED : ");
		}

		return "redirect:/";
	}
}
