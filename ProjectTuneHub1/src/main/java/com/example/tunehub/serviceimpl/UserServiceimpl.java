package com.example.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tunehub.entity.Song;
import com.example.tunehub.entity.User;
import com.example.tunehub.repository.UserRepository;
import com.example.tunehub.service.UserService;
@Service
public class UserServiceimpl implements UserService{
	@Autowired
	UserRepository userRepository;

	public String addUser(User user) {
		userRepository.save(user);

		return "user added successfully";
	}
	//logic to check the duplicate entries are there in DB
	public boolean emailExists(String email) {
		if(userRepository.findByEmail(email)!=null) {
			return true;
		}
		else {
			return false;
		}

	}
	public boolean vaidateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			String dbpwd=user.getPassword();
			String dbEmail=user.getEmail();

			if(password.equals(dbpwd) && email.equals(dbEmail)) {
				return true;
			}
		}
		return false;
	}

	public String getRole(String email) {
		User user = userRepository.findByEmail(email);
		return user.getRole();
	}
	@Override
	public User getUser(String email) {
		
		return userRepository.findByEmail(email);
	}
	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}
	



}
