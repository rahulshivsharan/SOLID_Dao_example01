package com.sol.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.sol.cn.ConnectionProvider;
import com.sol.cn.JPAConnectionProvider;
import com.sol.vo.UserVO;

public class JPAUserRepository implements UserRepository{

	private final ConnectionProvider<EntityManager> provider;
	
	public JPAUserRepository() {
		provider = new JPAConnectionProvider("demoApp");
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
