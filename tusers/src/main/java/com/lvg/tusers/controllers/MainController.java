package com.lvg.tusers.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lvg.tusers.config.R;
import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;
import com.lvg.tusers.utils.CodecsUtil;

@Controller
public class MainController implements R {
	private final String GREETING_STRING = "Hello everyone!";
	private final String ATR_CURRENT_USER = "currentUser";
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(ATR_CURRENT_USER);
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
				String currentPassword = CodecsUtil.getMD5(currentUser.getPassword());
				if (user.getEmail().equals(currentUser.getEmail())
						&& user.getPassword().equals(currentPassword)) {
					request.getSession().setAttribute(ATR_CURRENT_USER, user);
					model.addAttribute("userList", userDao.getAll());
					return "home";
				}
			}
		
		return "signin";
	}
	
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
		System.out.println("--------------REGISTRATION POST MAPPING----------");
		if(bindingResult.hasErrors()){
			System.out.println("USER REGISTRATION HAS: "+bindingResult.getFieldErrorCount()+" ERRORS");
			List<ObjectError> list = bindingResult.getAllErrors();
			for(ObjectError or : list){
				System.out.printf("%s - ErrorName \n", or.getObjectName());
				System.out.printf("%s - ErrorCode \n", or.getCode());
				System.out.printf("%s - ErrorMessage \n", or.getDefaultMessage());
			}
			return "registration";
		}
		
		currentSession.setAttribute(ATR_CURRENT_USER, user);
		return "home";
	}
}
