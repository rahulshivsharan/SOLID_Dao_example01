package com.sol.repo;

import java.util.List;

import com.sol.vo.UserVO;

public interface UserRepository {
	void saveUser();
	List<UserVO> getUsers();
}
