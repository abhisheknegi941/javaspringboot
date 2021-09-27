package com.nagarro.hrmanagerapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.hrmanagerapi.model.User;
import com.nagarro.hrmanagerapi.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		return "Testing Hello World";
	}

	/*Method to get a user*/
	@GetMapping("/user/{username}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable("username") String username) {
		Optional<User> user = this.userService.getUser(username);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(user));
	}

	// Adding a User
	@PostMapping("/user")
	public ResponseEntity<Void> addBook(@RequestBody User user) {
		try {
			this.userService.addUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
