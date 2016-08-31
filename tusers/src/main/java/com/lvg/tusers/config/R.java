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
		
		public String ERROR_MSG_404 = "PAGE NOT FOUND";
		public String ERROR_MSG_500 = "INTERNAL ERROR";
	}

}
