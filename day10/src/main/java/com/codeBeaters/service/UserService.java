package com.codeBeaters.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeBeaters.entity.User;
import com.codeBeaters.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository user;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Optional<User> getById(long uid){
		
		return user.findById(uid);
	}
	
	public List<User> getAllUser(){
		
		return user.findAll();
	}
	
    public String delete(long id) {
    	
    	if(user.existsById(id)) {
    		
    		user.deleteById(id);
    		return "Deleted . . .";
    	}
    	else {
    		
    		return "ID doesn't Exist. . .";
    	}
    }
    
    public String update(User u, long id) {
    	
    	User updateUser = user.findById(id).get();
    	
    	if(updateUser == null) {
    		
    		return "User Doesn't Exist. . .";
    	}
    	else {
    		
    		updateUser.setName(u.getName());;
    		updateUser.setEmail(u.getEmail());
    		updateUser.setPassword(passwordEncoder.encode(u.getPassword()));
    		
    		user.save(updateUser);
    		return "Updated. . .";
    	}
    }

}
