package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class ApiRoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/list-role")
	public List<RoleDto> getAll() {
		return roleService.getAll();
	}

	@GetMapping(value = "/detail-role/{roleId}")
	public RoleDto getRoleById(@PathVariable("roleId") String roleId) {
		return roleService.getRoleDto(Integer.parseInt(roleId));
	}

	@DeleteMapping(value = "/delete-role/{roleId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> deleteRoleById(@PathVariable("roleId") String roleId) {
		try {
			roleService.deleteRoleDto(Integer.parseInt(roleId));
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.ok("NOT OK");
		}

	}

	@PostMapping(value = "/add-role")
	@ResponseStatus(code = HttpStatus.OK)
	public void addRole(@RequestBody RoleDto roleDto) {
		roleService.addRoleDto(roleDto);
	}

	@PutMapping(value = "/update-role")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateRole(@RequestBody RoleDto roleDto) {
		roleService.updateRole(roleDto);
	}
	
	@GetMapping(value = "/get-role/{userId}")
	public List<RoleDto> getRoleByUser(@PathVariable("userId") Integer userId){
		return roleService.getRolesByUser(userId);
	}
}
