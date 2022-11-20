package com.exam.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userrole) throws Exception {
		// TODO Auto-generated method stub
		User local=this.userRepository.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("User Already Present !!");
			throw new Exception("User Already Exists !!"); 
		}else {
			for(UserRole ur:userrole) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userrole);
		    local=this.userRepository.save(user);
		}
		
		return local;
	}

	@Override
	public User findByUsername(String username) {
		
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
		this.userRepository.deleteById(id);
		
	}

	@Override
	public User updateUser(User user) {
		
		User u = new User();
		u.setId(user.getId());
		u.setAbout(user.getAbout());
		u.setEmailId(user.getEmailId());
		u.setEnable(user.getEnable());
		u.setName(user.getName());
		u.setPassword(user.getPassword());
		u.setNumber(user.getNumber());
		u.setProfile(user.getProfile());
		u.setUsername(user.getUsername());
		
		this.userRepository.save(u);
		return user;
		// TODO Auto-generated method stub
		
	}

}
