package com.lvg.tusers.controllers;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lvg.tusers.config.R;
import com.lvg.tusers.services.ImageService;
import com.lvg.tusers.services.UserService;



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
		}
		return "home";
	}

	
	@RequestMapping(value="img",method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	private byte[] getImageSource(@RequestParam("iid") Long imgId, HttpServletResponse response)throws IOException{	
		return new byte[12];
	}
}
