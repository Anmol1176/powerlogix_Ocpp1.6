package com.powerlogix.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.powerlogix.models.User;
import com.powerlogix.models.UserRole;
import com.powerlogix.repo.RoleRepository;
import com.powerlogix.repo.UserRepository;
import com.powerlogix.services.UserService;

@Service
public class UserServiceImpl implements UserService
{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	//creating user
	@Override
	public User saveUser(User user, Set<UserRole> userRoles) throws Exception 
	{
		
        User existingUser  = this.userRepository.findByUsername(user.getUsername());
        if(existingUser !=null)
        {
        	System.out.println("User is already present !!");
        	throw new Exception("User already exists !");
        }
        else
        {
           	//user create 
        	for(UserRole ur: userRoles)
        	{
        		roleRepository.save(ur.getRole());
        	}
        	user.getUserRoles().addAll(userRoles);

        	User savedUser = userRepository.save(user);
    		return savedUser;

        }
	}

}
