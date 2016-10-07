package com.lvg.tusers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileUploadController {
		
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	private String uploadFile(HttpServletRequest request, HttpServletResponse response, Object command, BindException error){
		
		
		return "home";
	}
}
