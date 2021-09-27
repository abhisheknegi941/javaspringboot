package com.nagarro.hrmanagerapi.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.hrmanagerapi.model.User;

public interface UserRepository extends CrudRepository<User, String> {

  public Optional<User> findById(String username);
}
