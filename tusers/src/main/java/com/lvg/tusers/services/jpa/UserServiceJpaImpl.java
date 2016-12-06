package com.lvg.tusers.services.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lvg.tusers.models.User;
import com.lvg.tusers.repositories.UserRepository;
import com.lvg.tusers.services.UserService;

@Repository
@Service("userService")
@Transactional
public class UserServiceJpaImpl implements UserService{
	private static final Logger LOG = Logger.getLogger(UserServiceJpaImpl.class);
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {		
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User user) {
		LOG.info("STARTING SAVE USER");
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		LOG.info("STARTING DELETE USER");
		userRepository.delete(user);
		LOG.info("USER WITH ID: "+user.getId()+" DELETED SUCCESSFULLY");
	}

	
	@Override
	@Transactional(readOnly = true)	
	public boolean isUserUnique(User user) {		
		for(User u : findAll()){
			if(u.getEmail().equalsIgnoreCase(user.getEmail()))
				return false;
		}
		return true;
	}
	
	
}
