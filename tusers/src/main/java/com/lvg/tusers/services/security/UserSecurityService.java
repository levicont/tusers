package com.lvg.tusers.services.security;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lvg.tusers.models.User;
import com.lvg.tusers.services.UserService;

@Repository
@Service("userSecurityService")
public class UserSecurityService implements UserDetailsService{
	private static final Logger LOG = Logger.getLogger(UserSecurityService.class);
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		for(User user : userService.findAll()){
			if(username.equalsIgnoreCase(user.getEmail()))
				return new UserDetailsImpl(user);
		}
		LOG.debug("User with username: "+username+" not found.");
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
