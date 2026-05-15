package com.sol.main;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;

import com.sol.cn.ConnectionProvider;
import com.sol.cn.JDBCConnectionProvider;
import com.sol.cn.JPAConnectionProvider;
import com.sol.config.DatabaseConfig;
import com.sol.factory.UserRepoFactory;
import com.sol.repo.UserRepository;
import com.sol.service.UserService;
import com.sol.vo.UserVO;

public class Application {
	
	private static void getUsersFromJDBC() {
		
		DatabaseConfig dbconfig = new DatabaseConfig( 
			"jdbc:postgresql://localhost:5432/postgres", 
			"postgres", 
			"rahul"
		);
		
		ConnectionProvider<Connection> provider = new JDBCConnectionProvider(	dbconfig.getUrl(), 
																				dbconfig.getUsername(),
																				dbconfig.getPassword());
		
		UserRepository repo =  UserRepoFactory.getInstance("JDBC", provider);
		
		
		UserService service = new UserService(repo);
		
		List<UserVO> userList = service.getUsers();
		userList.stream().forEach(System.out::print);
	}
	
	private static void getUsersFromJPA() {
		String persistenceUnitName = "demoApp";
		ConnectionProvider<EntityManager> provider = new JPAConnectionProvider(persistenceUnitName);
		
		UserRepository repo =  UserRepoFactory.getInstance("JPA", provider);
		
		
		UserService service = new UserService(repo);
		
		List<UserVO> userList = service.getUsers();
		userList.stream().forEach(System.out::print);
	}
	
	
	public static void main(String [] args) {
		try {
			getUsersFromJDBC();
			System.out.println("\n\n\n");
			getUsersFromJPA();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
