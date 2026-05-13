package com.sol.factory;

import com.sol.repo.JDBCUserRepository;
import com.sol.repo.JPAUserRepository;
import com.sol.repo.UserRepository;

public class UserRepoFactory {
	public static UserRepository getInstance(String instanceName) {
		if("JDBC".equalsIgnoreCase(instanceName)) {
			return new JDBCUserRepository();
		} else if("JPA".equalsIgnoreCase(instanceName)){
			return new JPAUserRepository();
		}else {
			throw new RuntimeException("Invalid instance name");
		}
	}
}
