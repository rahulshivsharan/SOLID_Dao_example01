package com.sol.cn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

public class JDBCConnectionProvider implements ConnectionProvider<Connection>{
	
	private final String url;
	private final String user;
	private final String password;
	

	public JDBCConnectionProvider(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		}catch(Exception e) {
			throw new RuntimeException("Failed to connect to JDBC", e);
		}
		
	}

	@Override
	public void releaseConnection(Connection connection) {
		try {
			if (connection != null) connection.close();
		}catch(SQLException e) {
			throw new RuntimeException("Failed to close JDBC connect ", e);			
		}
	}

}
