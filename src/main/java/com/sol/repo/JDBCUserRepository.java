package com.sol.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sol.cn.ConnectionProvider;
import com.sol.exception.UserRepositoryException;
import com.sol.vo.UserVO;

public class JDBCUserRepository implements UserRepository{
	
	private ConnectionProvider<Connection> provider;
	
	
	
	public JDBCUserRepository(ConnectionProvider<Connection> provider) {
		if(provider == null) {
			throw new IllegalArgumentException("Povider cannot be null");
		}
		this.provider = provider;
	}
	
	
	@Override
	public void saveUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserVO> getUsers() {
		Connection con = null;
		try {
			List<UserVO> userList = new ArrayList<UserVO>();
			
			con = provider.getConnection();
			PreparedStatement ps = con.prepareStatement("select id, username, password from userstbl");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				UserVO vo = new UserVO(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
				userList.add(vo);
			}
			
			return userList;
		}catch(SQLException sqle) {
			throw new UserRepositoryException("Failed to fetch users from database", sqle);
		} catch(Exception e) {
			throw new UserRepositoryException("Failed to fetch users", e);
		}finally {
			provider.releaseConnection(con);
		}	
		
		
	}

}
