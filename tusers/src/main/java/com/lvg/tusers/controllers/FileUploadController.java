package com.lvg.tusers.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lvg.tusers.config.R;
import com.lvg.tusers.models.Gallery;
import com.lvg.tusers.models.Image;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.ImageService;
import com.lvg.tusers.services.UserService;
import com.lvg.tusers.utils.ApplicationContextUtil;



@Controller
public class FileUploadController implements R{
	private static final Logger LOG = Logger.getLogger(FileUploadController.class);
	
	@Autowired
	ImageService imageService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	private String uploadFile(Model uiModel,
			HttpServletRequest request, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value="file", required=false)Part file,
			@RequestParam(value="fileName", required=false)String name)
			throws Exception {
		LOG.info("Starting upload file");
		LOG.info("UPLOADING FILE Has name: "+name);
		if (file != null){
			LOG.info("UPLOADING FILE Has name: "+file.getName());
			LOG.info("UPLOADING FILE Has content type: "+file.getContentType());
			LOG.info("UPLOADING FILE Has size: "+file.getSize());
			User user = ApplicationContextUtil.getUserFromSecurityContext(SecurityContextHolder.getContext().getAuthentication(), userService);
			Gallery gallery = null;
			for(Gallery g : user.getGalleries()){
				gallery = g;
				break;
			}
			Image newImage = new Image();
			newImage.setGallery(gallery);
			newImage.setName(file.getName());
			byte[] source = null;
			try {
				InputStream in = file.getInputStream();
				if (in == null){
					LOG.info("File inputstream is null");
					return "home";
				}
				source = IOUtils.toByteArray(in);
				newImage.setSource(source);
				imageService.save(newImage);
				LOG.info("Image file uploaded and saved in db with id: "+ newImage.getId());
			} catch (IOException ex) {
				LOG.info("Error saving uploaded file");
				return "home";
			}
			
		}		
		return "redirect:/";
	}

	
	@RequestMapping(value="img/{id}",method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	private byte[] getImageSource(@PathVariable("id") Long imgId, HttpServletResponse response)throws IOException{	
		LOG.info("Getting image from db");
		Image image = imageService.findById(imgId);
		if(image.getSource() != null){
			LOG.info("Image exists with id: "+imgId+" and has size: "+image.getSource().length+ " bytes");
		}else{
			LOG.info("Image not exists with id: "+imgId);
		}
		return image.getSource();
	}
}
