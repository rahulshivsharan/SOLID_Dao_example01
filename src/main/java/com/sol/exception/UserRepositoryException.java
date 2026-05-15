package com.sol.exception;

public class UserRepositoryException extends RuntimeException{
	public UserRepositoryException(String message, Throwable ex) {
		super(message, ex);
	}
}
