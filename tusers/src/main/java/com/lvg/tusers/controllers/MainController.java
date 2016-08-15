package com.lvg.tusers.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tusers.config.R;
import com.lvg.tusers.dao.jpa.UserDaoJpaImpl;
import com.lvg.tusers.models.User;

@Controller
public class MainController implements R {
	private final String GREETING_STRING = "Hello everyone!";

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("currentUser");
		if (null == user) {
			return "redirect:/signin";
		}
		return "home";
	}

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public ModelAndView signin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("signin");
		mv.addObject("greeting", GREETING_STRING);
		mv.addObject("user", new User());
		return mv;
	}

	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signin(@ModelAttribute User currentUser, Model model, HttpServletRequest request) {
		
		if (currentUser != null)
			for (User user : R.TestEnties.TEST_USERS) {
				if (user.getEmail().equals(currentUser.getEmail())
						&& user.getPassword().equals(currentUser.getPassword())) {
					request.getSession().setAttribute("currentUser", user);
					model.addAttribute("userList", new UserDaoJpaImpl().getAll());
					return "home";
				}
			}
		
		return "signin";
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String regisration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
}
