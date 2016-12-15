package com.lvg.tusers.services.jpa;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserServiceJpaImpl implements UserService, UserDetailsService{
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		for(User user : findAll()){
			if(username.equalsIgnoreCase(user.getEmail()))
				return new UserDetailsImpl(user);
		}
		throw new UsernameNotFoundException("User with username: "+username+" not found.");
	}
	
	private class UserDetailsImpl implements UserDetails{		
		private static final long serialVersionUID = 5766594076079860180L;
		
		private User user;
		
		public UserDetailsImpl(User user) {
			this.user = user;
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			GrantedAuthority authority = new GrantedAuthority() {				
				private static final long serialVersionUID = -5459325061917607618L;

				@Override
				public String getAuthority() {
					
					return "ROLE_USER";
				}
			};
			return Lists.newArrayList(authority);
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {			
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {			
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {			
			return true;
		}

		@Override
		public boolean isEnabled() {			
			return true;
		}
		
	}
	
	
	
	
}
