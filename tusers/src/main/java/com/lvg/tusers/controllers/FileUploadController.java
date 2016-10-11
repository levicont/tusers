package com.lvg.tusers.controllers;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tusers.models.UploadFileForm;

@Controller
public class FileUploadController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	private @ResponseBody String uploadFile(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Iterator<String> iterator = request.getFileNames();
		MultipartFile file = null;
		ModelAndView mv = new ModelAndView("home");
		while(iterator.hasNext()){
			file = request.getFile(iterator.next());
		}
		
		
		if(file == null){
			System.out.println("FILE IS NULL ");
			return null;
		}
		if (!file.isEmpty()) {
			System.out.println("FILE HAS UPLOADED : " + file.getOriginalFilename());
			byte[] mainImageSrc = file.getBytes();
			
			mv.addObject("mainImage", mainImageSrc);
			mv.addObject("uploadStatus", "Upload OK!");
			
		}else{
			System.out.println("FILE HAS NOT UPLOADED : ");
		}
		UploadFileForm result = new UploadFileForm();
		result.setFile(file);		
		return result.getFile().getOriginalFilename();
	}
}
