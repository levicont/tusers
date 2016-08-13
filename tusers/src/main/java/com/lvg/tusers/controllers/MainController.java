package com.lvg.tusers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	private final String GREETING_STRING = "Hello everyone!";
	
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("greeting", GREETING_STRING);
		return mv;
	}
}
