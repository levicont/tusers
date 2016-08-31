package com.lvg.tusers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tusers.config.R;
import com.lvg.tusers.exceptions.TUsersException;

@Controller
public class ErrorController {
	@RequestMapping("/errors/404.html")
	public ModelAndView error404(Model model)throws TUsersException{
		throw new TUsersException(R.Exceptions.ERROR_MSG_404);
	}
	
	@RequestMapping("/errors/500.html")
	public ModelAndView error500(Model model)throws TUsersException{
		throw new TUsersException(R.Exceptions.ERROR_MSG_500);
	}
}
