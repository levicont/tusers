package com.lvg.tusers.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lvg.tusers.config.R;
import com.lvg.tusers.models.Gallery;
import com.lvg.tusers.models.UploadFileForm;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.GalleryService;
import com.lvg.tusers.services.UserService;

@Controller
public class MainController implements R {	
	private final String ATR_CURRENT_USER = "currentUser";
	private final String ATR_REGISTRATION_OK = "registrationOK";
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private GalleryService galleryService;
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		request.setAttribute(ATR_CURRENT_USER, getUserFromSecurityContext(auth));
		return "home";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@RequestParam(name = "error", required = false) String error,
			Model model, HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("signin");
		if (error != null) {
			mv.addObject("error", R.Exceptions.ERROR_SIGNIN);
		}
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user")!=null )
			mv.getModel().put("user", session.getAttribute("user"));
		
		if (!mv.getModel().containsKey("user"))
			mv.addObject("user", new User());
		
		return mv;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }        
		return "redirect:/signin";
	}

	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String regisration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model,
			 RedirectAttributes redir, HttpServletRequest request) {
		
		if (!userService.isUserUnique(user)){
			bindingResult.addError(new FieldError("user","email",Exceptions.ERROR_INVALID_USR_EMAIL_NOT_UNIQUE));			
		}
		
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		
		String cryptedPass = bcrypt.encode(user.getPassword());		
		user.setPassword(cryptedPass);
		userService.add(user);
		Gallery gallery = new Gallery();
		gallery.setName(GalleryConfig.DEFAULT_GALLERY_NAME);
		gallery.setUser(user);
		galleryService.add(gallery);		
		redir.addFlashAttribute(ATR_REGISTRATION_OK, ATR_REGISTRATION_OK);
		redir.addFlashAttribute("user", user);
		return "redirect:/signin";
	}
	
//	@RequestMapping(value="upload", method = RequestMethod.POST)
//	private String uploadImg(Model model){
//		
//		return "home";
//	}

	private User getUserFromSecurityContext(Authentication authentication) {
		String email = authentication.getName();

		User currentUser = null;
		for (User user : userService.getAll()) {
			if (user.getEmail().equals(email))
				return user;
		}		
		return currentUser;		

	}
}
