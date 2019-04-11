package com.example.demo.dto;

import java.util.List;

public class RoleDto {
	private Integer roleId;

	private String name;

	private List<UserDto> userDtos;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void roleDto(String name) {
		this.name = name;
	}

	public List<UserDto> getUserDtos() {
		return userDtos;
	}

	public void setUserDtos(List<UserDto> userDtos) {
		this.userDtos = userDtos;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleDto(Integer roleId, String name) {
		super();
		this.roleId = roleId;
		this.name = name;
	}

	public RoleDto() {
		super();
	}
	
	
	

}
