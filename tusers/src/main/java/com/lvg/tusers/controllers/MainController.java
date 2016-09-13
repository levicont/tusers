package com.lvg.tusers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tusers.config.R;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.UserService;
import com.lvg.tusers.utils.CodecsUtil;

@Controller
public class MainController implements R {
	private final String GREETING_STRING = "Hello everyone!";
	private final String ATR_CURRENT_USER = "currentUser";
	
	@Autowired
	private UserService userService;
	
	/*@RequestMapping("/")
	public String index(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ATR_CURRENT_USER);
		if (null == user) {
			return "redirect:/signin";
		}
		return "home";
	}

	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam(name="error", required = false) String error, HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("signin");
		if(error != null){
			mv.addObject("error", R.Exceptions.ERROR_SIGNIN);
		}
		mv.addObject("user", new User());
		return mv;
	}
	*/
	
	@RequestMapping(value= "/home", method= RequestMethod.GET)
	public String index(HttpServletRequest request) {
		
		return "home";
	}
	
	@RequestMapping(value = {"/signin", "/"}, method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam(name="error", required = false) String error, HttpServletRequest request) {
		
		
		ModelAndView mv = new ModelAndView("signin");
		if(error != null){
			mv.addObject("error", R.Exceptions.ERROR_SIGNIN);
		}
		mv.addObject("user", new User());
		return mv;
	}

	/*@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signin(@ModelAttribute User currentUser, Model model, HttpServletRequest request) {
		
		if (currentUser != null)
			for (User user : R.TestEnties.TEST_USERS) {
				String currentPassword = CodecsUtil.getMD5(currentUser.getPassword());
				if (user.getEmail().equals(currentUser.getEmail())
						&& user.getPassword().equals(currentPassword)) {
					request.getSession().setAttribute(ATR_CURRENT_USER, user);
					model.addAttribute("userList", userService.getAll());
					return "home";
				}
			}
		model.addAttribute(R.Exceptions.ATR_ERROR_MESSAGE, R.Exceptions.ERROR_SIGNIN);
		return "signin";
	}
	*/
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		HttpSession currentSession = request.getSession();
		currentSession.removeAttribute(ATR_CURRENT_USER);
		currentSession.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String regisration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value= "registration", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, HttpServletRequest request){
		HttpSession currentSession = request.getSession();
		
		if(bindingResult.hasErrors()){			
			return "registration";
		}
		
		currentSession.setAttribute(ATR_CURRENT_USER, user);
		return "home";
	}
}
