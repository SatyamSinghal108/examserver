package com.exam.service;

import java.util.Set;

import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface UserService {
	
	//create new user
	public User createUser(User user, Set<UserRole> userrole) throws Exception;

	//get existing user
	public User findByUsername(String username);
	
	//delete user by id
	public void deleteById(Long id);
	
	//updating user
	public User updateUser(User user);
}
