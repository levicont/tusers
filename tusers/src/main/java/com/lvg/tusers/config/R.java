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
			u1.setPassword("1");
			u1.setInfo("This is Mike Oldfield");
			u1.setBirthday(new Date());
			add(u1);
			
		}};
		
	}

}
