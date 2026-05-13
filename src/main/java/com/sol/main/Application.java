package com.sol.main;

import java.util.List;

import com.sol.factory.UserRepoFactory;
import com.sol.repo.UserRepository;
import com.sol.service.UserService;
import com.sol.vo.UserVO;

public class Application {
	
	private static void getUsersFromJDBC() {
		UserRepository repo = UserRepoFactory.getInstance("JDBC");		
		UserService service = new UserService(repo);
		
		List<UserVO> userList = service.getUsers();
		userList.stream().forEach(System.out::print);
	}
	
	private static void getUsersFromJPA() {
		UserRepository repo = UserRepoFactory.getInstance("JPA");		
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
