package com.sol.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.sol.cn.ConnectionProvider;
import com.sol.entity.UserEntity;
import com.sol.exception.UserRepositoryException;
import com.sol.vo.UserVO;

public class JPAUserRepository implements UserRepository{

	private ConnectionProvider<EntityManager> provider;
		
	
	public JPAUserRepository(ConnectionProvider<EntityManager> provider) {
		if(provider == null) {
			throw new IllegalArgumentException("Povider cannot be null");
		}
		this.provider = provider;
	}
	


	@Override
	public void saveUser() {
		
	}

	@Override
	public List<UserVO> getUsers() {		
		EntityManager em = null;
		try {
			em = provider.getConnection();
			TypedQuery<UserEntity> query = em.createQuery("select u from UserEntity u", UserEntity.class); 
			List<UserEntity> userList = query.getResultList();
			
			List<UserVO> list  = userList.stream().map((entity) -> new UserVO(entity)).collect(Collectors.toList());			
			return list;
		}catch(Exception e) {
			throw new UserRepositoryException("Not able to fetch Users from JPA ",e);
		}finally {
			provider.releaseConnection(em);
		}
		 
	}

}
