package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SortDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.custom.UserRepositoryCustom;
import com.example.demo.service.UserService;
import com.example.demo.untils.Constant;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class ApiUserController {
	@Autowired
	UserService userService;
	@Autowired
	private UserRepositoryCustom repo;
    @Autowired
    private UserRepository repository;
	@PostMapping(value = "/list-user")
	public List<UserDto> getAll(@RequestBody SortDto sortDto) {
		// sortDto.setStatus("1");
		// sortDto.setRoleName("ROLE_ADMIN");
		return repo.getAllUser(sortDto);
	}

	@GetMapping(value = "/detail-user/{payload}")
	public User getUser(@PathVariable("payload") Integer userId) {
		User user=repository.findOneByUserId(userId);
		return user;
	}

	@DeleteMapping(value = "/delete-user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
		try {
			userService.deleteUser(Integer.parseInt(userId));
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.ok("NOT OK");
		}
	}

	@PostMapping(value = "/add-user")
	public void addUser(@RequestBody UserDto userDto) {
		userService.addUser(userDto);

	}

	@PutMapping(value = "/update-user/{userId}")
	public void updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDto) {
		userService.updateUser(userDto);
	}

	@PostMapping(value = "edit/role/{userId}")
	public void editRole(@PathVariable("userId") Integer userId, @RequestBody Integer[] roleId) {
		userService.editRoleUser(userId, roleId);
	}

//	@PostMapping(value = "/validate-fullName/{userId}")
//	public String validateFullName(@PathVariable("userId") Integer userId, @RequestBody String oldName) {
//		if (userService.validateFullNameByUser(oldName, userId) == null) {
//			return "";
//		} else {
//			return Constant.error;
//		}
//	}

	@GetMapping(value = "/users")
	public List<User> getUserDto() {
		List<User> users=repository.findAll();
		return users;
	}
}
