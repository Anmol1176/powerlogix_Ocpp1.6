package com.powerlogix.services;

import java.util.Set;

import com.powerlogix.models.User;
import com.powerlogix.models.UserRole;

public interface UserService 
{
   //creating user 
	public User saveUser(User user,Set<UserRole> userRoles) throws Exception;
}
