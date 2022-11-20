package com.exam.examAPI;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
//import com.exam.repository.RoleRepository;
//import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserAPI {

	@Autowired
	private UserService userService;
	private Long a=45L,b=46L,random;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		user.setAbout("Good Person");
		//encoding password with BCryptPasswordEncoder
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole> userroles=new HashSet<UserRole>(); 
		Role role=new Role();
		random = new Random().nextBoolean() ? a : b;
		if(random==a) {
			role.setRid(a);
			role.setType("NORMAL");
		}else {
			role.setRid(b);
			role.setType("ADMIN");
		}
		
		UserRole ur=new UserRole();
		ur.setRole(role);
		ur.setUser(user);
		
		userroles.add(ur);
		return this.userService.createUser(user,userroles);
		
	}
	
	@GetMapping("/{username}")
	public User getUSer(@PathVariable("username") String username) {
		return this.userService.findByUsername(username);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteById(@PathVariable("userId") Long userId) {
		
		this.userService.deleteById(userId);
	}
	
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {
		return this.userService.updateUser(user);
		
	}
}
