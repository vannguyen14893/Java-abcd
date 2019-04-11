package com.example.demo.dto;

import java.io.Serializable;

public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private Integer status;
	private String name;
	private String fileName;
//	private List<RoleDto> roleDtos;
//	private GroupDto groupDto;

	
	
	public Integer getUserId() {
		return userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UserDto() {
	super();
}

	public UserDto(Integer userId, String userName, String password, String fullName, String email, String phone,
			Integer status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

//	
//	public GroupDto getGroupDto() {
//		return groupDto;
//	}
//
//	public void setGroupDto(GroupDto groupDto) {
//		this.groupDto = groupDto;
//	}
//
//	public List<RoleDto> getRoleDtos() {
//		return roleDtos;
//	}
//
//	public void setRoleDtos(List<RoleDto> roleDtos) {
//		this.roleDtos = roleDtos;
//	}

}
