package com.sol.factory;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sol.cn.ConnectionProvider;
import com.sol.repo.JDBCUserRepository;
import com.sol.repo.JPAUserRepository;
import com.sol.repo.UserRepository;

public class UserRepoFactory {
	
	private static final Map<String ,Class<? extends UserRepository>> REPO_MAP = new HashMap<String, Class<? extends UserRepository>>();
	
	static {
		REPO_MAP.put("JDBC", JDBCUserRepository.class);
		REPO_MAP.put("JPA", JPAUserRepository.class);
	}
	
	public static UserRepository getInstance(String instanceName, ConnectionProvider provider) {
		try {
			Class<? extends UserRepository> clazz = REPO_MAP.get(instanceName);
			
			if(clazz == null) {
				throw new RuntimeException("Invalid Instance "+instanceName);
			}
			
			Constructor<?> constructor =  clazz.getConstructor(ConnectionProvider.class);
			UserRepository repo = (UserRepository) constructor.newInstance(provider);
			return repo;
		}catch(Exception e) {
			throw new RuntimeException("Failed to instantiate: "+instanceName, e);
		}
		
	}
}
