package com.sol.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

import com.sol.repo.UserRepository;
import com.sol.vo.UserVO;

public class UserService {
	private UserRepository repo;
	
	
	public UserService(UserRepository repo) {
		
		this.repo = Optional.of(repo).orElseThrow(() -> {
			throw new IllegalArgumentException("Repository cannot be null");
		});
	}
	
	public List<UserVO> getUsers(){
		return repo.getUsers();
	}
}
