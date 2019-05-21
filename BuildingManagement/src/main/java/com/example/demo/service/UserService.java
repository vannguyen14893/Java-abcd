package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
//	@Autowired
//	GroupRepository groupRepository;
	@Autowired
	private RoleRepository repository;

	public List<UserDto> getAll() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setEmail(user.getEmail());
			userDto.setFullName(user.getFullName());
			userDto.setPassword(user.getPassword());
			userDto.setUserName(user.getUserName());
			userDto.setPhone(user.getPhone());
			return userDto;
		}).collect(Collectors.toList());
		return userDtos;
	}

	public UserDto getUserDto(Integer userId) {
		User user = userRepository.findById(userId).get();
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setEmail(user.getEmail());
		userDto.setFullName(user.getFullName());
		userDto.setPassword(user.getPassword());
		userDto.setUserName(user.getUserName());
		userDto.setPhone(user.getPhone());

		return userDto;
	}

	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			userRepository.delete(user);

		}
	}

	public void addUser(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setUserName(userDto.getUserName());
		user.setFullName(userDto.getFullName());
		user.setPassword(userDto.getPassword());
		user.setPhone(userDto.getPhone());
		user.setStatus(userDto.getStatus());
		userRepository.save(user);

	}

	public void updateUser(UserDto userDto) {
		User user = userRepository.findById(userDto.getUserId()).get();
		if (user != null) {
			user.setUserId(userDto.getUserId());
			user.setEmail(userDto.getEmail());
			user.setUserName(userDto.getUserName());
			//user.setFullName(userDto.getFullName());
			user.setPassword(userDto.getPassword());
			//user.setPhone(userDto.getPhone());
			//user.setStatus(userDto.getStatus());

			userRepository.save(user);

		}
	}

	public void editRoleUser(Integer userId, Integer[] roleId) {

		User user = userRepository.findById(userId).get();
		user.getRoles().clear();
		List<Role> list = new ArrayList<Role>();
		for (int i = 0; i < roleId.length; i++) {
			list.add(repository.getOne(roleId[i]));
		}
		user.setRoles(list);
		userRepository.save(user);

	}

	public User validateFullNameByUser(String oldName, Integer userId) {
		User userOld = userRepository.findOneByUserId(userId);
		if (!userOld.getFullName().equals(oldName)) {
			User user = userRepository.findOneByFullName(oldName);
			if (user != null) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}