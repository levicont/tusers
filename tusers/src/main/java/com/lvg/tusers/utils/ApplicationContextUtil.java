package com.lvg.tusers.utils;

import org.springframework.security.core.Authentication;

import com.lvg.tusers.models.User;
import com.lvg.tusers.services.UserService;

public class ApplicationContextUtil {
	
	public static User getUserFromSecurityContext(Authentication authentication, UserService userService) {
		String email = authentication.getName();

		User currentUser = null;
		for (User user : userService.getAll()) {
			if (user.getEmail().equals(email))
				return user;
		}		
		return currentUser;		
	}
}
