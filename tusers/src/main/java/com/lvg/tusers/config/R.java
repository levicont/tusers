package com.lvg.tusers.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lvg.tusers.models.User;

public interface R {
	public interface TestEnties{
		List<User> TEST_USERS = new ArrayList<User>()		
		{{
			User u1 = new User();
			u1.setId(1L);
			u1.setName("Mike");
			u1.setSurname("Oldfield");
			u1.setEmail("mike@d.com");
			u1.setPassword("c4ca4238a0b923820dcc509a6f75849b");
			u1.setInfo("This is Mike Oldfield");
			u1.setBirthday(new Date());
			add(u1);
			
		}};
		
	}
	
	public interface Exceptions{
		public String ATR_ERROR_MESSAGE = "errorMessage";
		
		public String ERROR_SIGNIN = "E-mail or password is not valid";
		public String ERROR_INVALID_USR_NAME = "User name is not valid";
		public String ERROR_INVALID_USR_SURNAME = "User surname is not valid";
		public String ERROR_INVALID_USR_BIRTHDAY = "User birthday is not valid";
		public String ERROR_INVALID_USR_EMAIL = "User email is not valid";
		public String ERROR_INVALID_USR_PASSWORD = "User password is not valid";
		
		public String ERROR_MSG_404 = "404 PAGE NOT FOUND";
		public String ERROR_MSG_500 = "500 INTERNAL ERROR";
	}

}
