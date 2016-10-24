package com.lvg.tusers.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tusers.config.R;
import com.lvg.tusers.config.R.UserConfig;
import com.lvg.tusers.models.Gallery;
import com.lvg.tusers.models.Image;
import com.lvg.tusers.models.UploadFileForm;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.ImageService;
import com.lvg.tusers.services.UserService;
import com.lvg.tusers.utils.ApplicationContextUtil;

@Controller
public class FileUploadController implements R{
	private static final int DEFAULT_FILE_BUFFER_SIZE = 1024*1024;
	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	
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
			Image image = new Image();
			image.setSource(mainImageSrc);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User currentUser = ApplicationContextUtil.getUserFromSecurityContext(auth, userService);
			System.out.println("CURRENT USER : "+ currentUser);
			if(currentUser != null){
				Gallery gallery = currentUser.getGalleries().iterator().next();
				if(null != gallery){
					image.setGallery(gallery);
					imageService.add(image);
				}
			}
			 
			mv.addObject("mainImage", mainImageSrc);
			mv.addObject("uploadStatus", "Upload OK!");
			
		}else{
			System.out.println("FILE HAS NOT UPLOADED : ");
		}
		UploadFileForm result = new UploadFileForm();
		result.setFile(file);		
		return result.getFile().getOriginalFilename();
	}

	@ResponseBody
	@RequestMapping(value="img",method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	private byte[] getImageSource(@RequestParam("iid") Long imgId)throws IOException{
		System.out.println("IMAGE ID: "+imgId);
		Image image = imageService.getById(imgId);
		if(image == null)
			return null;
		
		// Init servlet response.
		System.out.println("SELECTED IMAGE IS: "+image);
		InputStream in = new BufferedInputStream(new ByteArrayInputStream(image.getSource()));
		return IOUtils.toByteArray(in);
	}
}
