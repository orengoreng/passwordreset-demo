package com.gumtree.demo.business;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gumtree.demo.data.User;
import com.gumtree.demo.data.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public Optional<User> findByUsername(String username) {
		return userRepo.findById(username);
	}

	@Transactional
	public User save(User user) {
		return userRepo.save(user);
	}

}
