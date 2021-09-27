package com.nagarro.hrmanagerapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.hrmanagerapi.dao.UserRepository;
import com.nagarro.hrmanagerapi.model.User;

/*Service class for User*/
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/* Method to get a user */
	public Optional<User> getUser(String username) {
		Optional<User> user = null;

		try {
			user = this.userRepository.findById(username);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	/* Method to add a user */
	public User addUser(User user) {
		User result = this.userRepository.save(user);
		return result;
	}

}
