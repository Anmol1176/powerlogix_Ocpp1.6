package com.powerlogix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.powerlogix.models.User;
import com.powerlogix.repo.UserRepository;

@Service
public class CustomUserDetails implements  UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		//fetching user from database
		
		 User   user = userRepository.findByUsername(username);

		 if(user==null)
		 {
			 throw new UsernameNotFoundException("Could Not Found User !");
		 }
		 

	        return user;
	}
		 
}
