package com.lvg.tusers.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lvg.tusers.config.R;
import com.lvg.tusers.models.Gallery;
import com.lvg.tusers.models.Image;
import com.lvg.tusers.models.UploadFileForm;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.ImageService;
import com.lvg.tusers.services.UserService;
import com.lvg.tusers.utils.ApplicationContextUtil;



@Controller
public class FileUploadController implements R{
	
	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	private @ResponseBody String uploadFile(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Iterator<String> iterator = request.getFileNames();
		MultipartFile file = null;		
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
			System.out.println("RETURNED BYTES: "+mainImageSrc);
			Image image = imageService.getById(1);
			image.setSource(mainImageSrc);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User currentUser = ApplicationContextUtil.getUserFromSecurityContext(auth, userService);
			System.out.println("CURRENT USER : "+ currentUser);
			if(currentUser != null){
				Gallery gallery = currentUser.getGalleries().iterator().next();
				if(null != gallery){
					image.setGallery(gallery);
					imageService.update(image);
				}
			}			 
			
			
		}else{
			System.out.println("FILE HAS NOT UPLOADED : ");
		}
		UploadFileForm result = new UploadFileForm();
		result.setFile(file);		
		return file.getContentType();
	}

	
	@RequestMapping(value="img",method = RequestMethod.GET)
	private void getImageSource(@RequestParam("iid") Long imgId, HttpServletResponse response)throws IOException{	
		
		System.out.println("IMAGE ID: "+imgId);
		Image image = imageService.getById(imgId);
		if(image == null)
			return;		
		// Init servlet response.
		System.out.println("SELECTED IMAGE IS: "+image);		
		System.out.println("RETURNED BYTES: "+image.getSource());
		
		
		StringBuilder imageString = new StringBuilder();
		
		imageString.append("data:image/jpeg;base64,");
		imageString.append(Base64.encodeBase64String(image.getSource())); //bytes will be image byte[] come from DB 
		String imageStr = imageString.toString();				
		
		response.reset();
		response.setBufferSize(1024*10);
		response.setHeader("Content-Type", "image/jpeg");
		response.setHeader("Cache-Control", "no-store");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType("image/jpeg");        
        response.setContentLength(image.getSource().length);
        response.setStatus(200);
        response.setHeader("Content-Disposition", "inline");
        response.flushBuffer();
	   
     // Prepare streams.
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            // Open streams.
            input = new BufferedInputStream(new ByteArrayInputStream(image.getSource()));
            output = new BufferedOutputStream(response.getOutputStream());
            // Write file contents to response.
            byte[] buffer = new byte[1024*10];
            int length = 0;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
                output.flush();
                response.flushBuffer();
            }
        } finally {
            // Gently close streams.
            input.close();
            output.close();
        }
        
        
        
		
				
		/*ResponseEntity.ok()
				.contentLength(image.getSource().length)
				.contentType(MediaType.IMAGE_JPEG)
				.body(new InputStreamResource(new ByteArrayInputStream(image.getSource())));		  
		 * */		
	}
}
