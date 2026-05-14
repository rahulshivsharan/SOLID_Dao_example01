package com.sol.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.sol.cn.ConnectionProvider;
import com.sol.vo.UserVO;

public class JPAUserRepository implements UserRepository{

	private ConnectionProvider<EntityManager> provider;
	
	public JPAUserRepository() { 
		
	}
	
	public JPAUserRepository(ConnectionProvider<EntityManager> provider) {
		this.provider = provider;
	}
	
	
	
	public ConnectionProvider<EntityManager> getProvider() {
		return provider;
	}

	public void setProvider(ConnectionProvider<EntityManager> provider) {
		this.provider = provider;
	}

	@Override
	public void saveUser() {
		
	}

	@Override
	public List<UserVO> getUsers() {
		List<UserVO> list = null;
		EntityManager em = null;
		try {
			em = provider.getConnection();
			TypedQuery<UserVO> query = em.createQuery("select u from UserVO u", UserVO.class); 
			list = query.getResultList();
			return list;
		}catch(Exception e) {
			throw new RuntimeException("Not able to fetch Users ",e);
		}finally {
			provider.releaseConnection(em);
		}
		 
	}

}
