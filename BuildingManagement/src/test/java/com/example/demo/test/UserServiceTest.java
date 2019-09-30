package com.example.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

class UserServiceTest extends UserService {
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testList() {
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUserId(1);
		user.setUserName("ndvan");
		user.setPassword("123");
		User user2 = new User();
		user2.setUserId(1);
		user2.setUserName("ndvan");
		user2.setPassword("123");
		users.add(user);
		users.add(user2);
		when(userRepository.findAll()).thenReturn(users);
		List<UserDto> userDtos = users.stream().map(user3 -> {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setPassword(user.getPassword());
			userDto.setUserName(user.getUserName());
			return userDto;
		}).collect(Collectors.toList());
		assertEquals(2, userDtos.size());

	}

	@Test
	void testGetOne() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("ndvan");
		user.setPassword("123");
		when(userRepository.findOneByUserId(anyInt())).thenReturn(user);
		UserDto userDto = userService.getUserDto(user.getUserId());
		assertNotNull(userDto);
		assertEquals("ndvan", userDto.getUserName());
	}

	@Test
	void test_NullPointerException() {
		when(userRepository.findById(anyInt())).thenReturn(null);
		assertThrows(NullPointerException.class, () -> {
			userService.deleteUser(99999);
		});
	}

	@Test
	void testDelete() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("ndvan");
		user.setPassword("123");
		when(userRepository.findOneByUserId(anyInt())).thenReturn(user);
		userService.deleteUser(user.getUserId());
		when(userRepository.findById(anyInt())).thenReturn(null);
	}

	@Test
	void testCreate() {
		User user = new User();
		user.setUserName("ndvan");
		user.setPassword("123");
		when(userRepository.save(user)).thenReturn(user);
		assertNotNull(user);
		assertEquals("ndvan", user.getUserName());
	}

}
