package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SortDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.custom.UserRepositoryCustom;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.untils.Constant;
import com.example.demo.untils.Mail;

@RestController
//@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class ApiUserController {
	@Autowired
	UserService userService;
	@Autowired
	private UserRepositoryCustom repo;
	@Autowired
	private UserRepository repository;
	@Autowired
	private EmailService emailService;
	@PostMapping(value = "/list-user")
	public List<UserDto> getAll(@RequestBody SortDto sortDto) {
		// sortDto.setStatus("1");
		// sortDto.setRoleName("ROLE_ADMIN");
		return repo.getAllUser(sortDto);
	}

	@GetMapping(value = "/detail-user/{payload}")
	public User getUser(@PathVariable("payload") Integer userId) throws MessagingException, IOException {
		User user = repository.findOneByUserId(userId);
		
		return user;
	}
	@CacheEvict(value = "users", key = "#userId")
	@DeleteMapping(value = "/delete-user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/add-user")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult result) {
		List<String> errors = new ArrayList<String>();
		try {		
			if (result.hasErrors()) {
				List<FieldError> fieldErrors = result.getFieldErrors();
				for (FieldError error : fieldErrors) {
					errors.add(error.getField() + " " + error.getDefaultMessage());
				}
				return ResponseEntity.badRequest().body(errors);
			} else {
				repository.save(user);
				return ResponseEntity.ok("ok");
			}

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@CachePut(value = "users", key = "#userDto.userId")
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
	@Cacheable(value = "users")
	@GetMapping(value = "/users")
	public Page<User> getUserDto(Pageable pageable) {
		Pageable pageRequest=PageRequest.of(0, 10);
		
//		try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
		Page<User> users = repository.findAll(pageRequest);
		
		return users;
	}
	@Scheduled(cron = "0 22 21 * * *")
	public void sendMail() throws MessagingException, IOException {
		Mail mail = new Mail();
		mail.setFrom("ducvan14893@gmail.com");
		mail.setTo("ducvan14893@gmail.com");
		mail.setSubject("Sending Email with Thymeleaf HTML Template Example");
		Map model = new HashMap();
		model.put("name", "Memorynotfound.com");
		model.put("location", "Belgium");
		model.put("signature", "https://memorynotfound.com");
		mail.setModel(model);
		emailService.sendSimpleMessage(mail);
	}
}
