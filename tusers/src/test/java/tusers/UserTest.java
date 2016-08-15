package tusers;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lvg.tusers.dao.UserDao;
import com.lvg.tusers.models.User;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/applicationContext.xml"})
public class UserTest {
	
	@Autowired
	UserDao userDao;
	Long id;
	
	@Before
	public void init(){
		id = 1L;
	}
	
	@Test
	public void listUserTest(){
		List<User> list = userDao.getAll();
		assertNotNull(list);
	}
	
}
