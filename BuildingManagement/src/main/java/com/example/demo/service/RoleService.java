package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.RoleDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.untils.ExeptionMessage;

@Service
@Transactional
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	public List<RoleDto> getAll() {
		List<Role> roles = roleRepository.findAll();
		List<RoleDto> roleDtos = roles.stream().map(role -> {
			RoleDto roleDto = new RoleDto();
			roleDto.setRoleId(role.getRoleId());
			roleDto.setName(role.getName());
			List<User> users = role.getUsers();
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user : users) {
				UserDto userDto = new UserDto();
				userDto.setUserId(user.getUserId());
//				userDto.setEmail(user.getEmail());
//				userDto.setFullName(user.getFullName());
//				userDto.setPassword(user.getPassword());
//				userDto.setPhone(user.getPhone());
//				userDto.setStatus(user.getStatus());
				userDtos.add(userDto);
				roleDto.setUserDtos(userDtos);
			}
			return roleDto;
		}).collect(Collectors.toList());
		return roleDtos;
	}

	public RoleDto getRoleDto(int roleId) {
		Optional<Role> findById = roleRepository.findById(roleId);
		RoleDto roleDto = new RoleDto();
		if (findById.isPresent()) {
			Role role = findById.get();
			roleDto.setRoleId(role.getRoleId());
			roleDto.setName(role.getName());
			List<User> users = role.getUsers();
			List<UserDto> userDtos = new ArrayList<UserDto>();
			for (User user : users) {
				UserDto userDto = new UserDto();
				userDto.setUserId(user.getUserId());
//				userDto.setEmail(user.getEmail());
//				userDto.setFullName(user.getFullName());
//				userDto.setPassword(user.getPassword());
//				userDto.setPhone(user.getPhone());
//				userDto.setStatus(user.getStatus());
				userDtos.add(userDto);
				roleDto.setUserDtos(userDtos);
			}
		}

		return roleDto;
	}

	public void deleteRoleDto(Integer roleId) throws ExeptionMessage {
		Role role = roleRepository.findById(roleId).get();
		if (role != null) {
			roleRepository.delete(role);
		} else {
			throw new ExeptionMessage("lol");
		}
	}

	public void addRoleDto(RoleDto roleDto) {
		Role role = new Role();
		role.setName(roleDto.getName());
		roleRepository.save(role);
	}

	public void updateRole(RoleDto roleDto) {
		Role role = roleRepository.findById(roleDto.getRoleId()).get();
		if (role != null) {
			role.setRoleId(roleDto.getRoleId());
			role.setName(roleDto.getName());
			roleRepository.save(role);
		}
	}

	public List<RoleDto> getRolesByUser(Integer userId) {
		List<RoleDto> roleDtos = new ArrayList<RoleDto>();
		User user = userRepository.getOne(userId);
		for (Role item : user.getRoles()) {
			RoleDto dto = new RoleDto(item.getRoleId(), item.getName());
			roleDtos.add(dto);

		}
		return roleDtos;
	}
	RestTemplate restTemplate = new RestTemplate();
    public List<Role> getUser() {
        String url = "http://localhost:8080/list-role";
        Role[] response = restTemplate.getForObject(url, Role[].class);
        return Arrays.asList(response);
    }
    @Async
    public CompletableFuture<List<Role>> getCountriesByLanguage(String role1) {
        String url = "http://localhost:8080/get-roleName" + "?roleName=" + role1 ;
        Role[] response = restTemplate.getForObject(url, Role[].class);

        return CompletableFuture.completedFuture(Arrays.asList(response));
    }

    @Async
    public CompletableFuture<List<Role>> getCountriesByRegion(String role2) {
        String url = "http://localhost:8080/get-roleName"  + "?roleName=" + role2;
        Role[] response = restTemplate.getForObject(url, Role[].class);
        return CompletableFuture.completedFuture(Arrays.asList(response));
        
    }
}
