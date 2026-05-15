package com.sol.vo;

import com.sol.entity.UserEntity;

public class UserVO {

	private Integer id;
	private String name;
	private String password;

	public UserVO() {
	}
	
	public UserVO(UserEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.password = entity.getPassword();
	}	

	public UserVO(Integer id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return String.format("User Id '%d', User name '%s', user password '%s' \n", this.id, this.name, this.password);
	}
}
