package com.sol.service;

import java.util.List;

import com.sol.repo.UserRepository;
import com.sol.vo.UserVO;

public class UserService {
	private UserRepository repo;
	
	
	
	public UserService(UserRepository repo) {
		if(repo == null) {
			throw new RuntimeException("Repository cannot be null");
		}
		this.repo = repo;
	}
	
	public List<UserVO> getUsers(){
		return repo.getUsers();
	}
}
