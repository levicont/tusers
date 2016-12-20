package com.lvg.tusers.utils;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;

import com.lvg.tusers.models.User;
import com.lvg.tusers.services.UserService;

public class ApplicationContextUtil {
	private static final Logger LOG = Logger.getLogger(ApplicationContextUtil.class); 
	
	public static User getUserFromSecurityContext(Authentication authentication, UserService userService) {
		if(authentication == null){
			LOG.info("Authentication instance is null");
			return null;
		}
		String email = authentication.getName();

		User currentUser = null;
		for (User user : userService.findAll()) {
			if (user.getEmail().equals(email))
				return user;
		}		
		return currentUser;		
	}
}
