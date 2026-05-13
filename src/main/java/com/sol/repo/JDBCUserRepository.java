package com.sol.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sol.cn.ConnectionProvider;
import com.sol.cn.JDBCConnectionProvider;
import com.sol.vo.UserVO;

public class JDBCUserRepository implements UserRepository{
	
	private final ConnectionProvider<Connection> provider;
	
	public JDBCUserRepository() {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "rahul";
		this.provider = new JDBCConnectionProvider(url, username, password);
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
		}catch(Exception e) {
			throw new RuntimeException("Failed to fetch users "+e.getMessage());
		}finally {
			provider.releaseConnection(con);
		}	
		
		
	}

}
