package com.sol.cn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConnectionProvider implements ConnectionProvider<EntityManager>{

	private EntityManagerFactory emf;
	
	public JPAConnectionProvider(String persistenceUnit) {
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
	}
	
	@Override
	public EntityManager getConnection() {
		try {
			EntityManager em = emf.createEntityManager();
			return em;
		}catch(Exception e) {
			throw new RuntimeException("Faied to connect and create EntityManager ",e);
		}
		
	}

	@Override
	public void releaseConnection(EntityManager em) {
		try {
			if(em != null) em.close();
		}catch(Exception e) {
			throw new RuntimeException("Failed to close the EntityManager ",e);
		}
		
	}

}
