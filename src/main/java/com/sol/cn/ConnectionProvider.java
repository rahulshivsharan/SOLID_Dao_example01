package com.sol.cn;

public interface ConnectionProvider<T> {
	public T getConnection();
	void releaseConnection(T connection);
}
