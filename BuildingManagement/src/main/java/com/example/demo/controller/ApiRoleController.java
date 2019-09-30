package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Api(value = "ApiRoleController")
@RestController

//@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class ApiRoleController {
	private static final Logger logger = Logger.getLogger(ApiRoleController.class);
	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository roleRepository;

	@ApiOperation(httpMethod = "GET", value = "Get all European and French speaking countries", response = String.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Countries not found"),
			@ApiResponse(code = 500, message = "The countries could not be fetched") })
	@Cacheable(value = "roles")
	@GetMapping(value = "/list-role")
	public List<RoleDto> getAll() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			List<RoleDto> roles = roleService.getAll();
			logger.error("xin chào các bạn");
			return roles;

		} catch (Exception e) {
			logger.error("error log controller" + e);
		}
		return null;
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
	public List<RoleDto> getRoleByUser(@PathVariable("userId") Integer userId) {
		return roleService.getRolesByUser(userId);
	}

	@GetMapping(value = "/get-roleName")
	public List<Role> getRoleByName(@RequestParam("roleName") String roleName) {
		return roleRepository.findByName(roleName);
	}

	@Scheduled(cron = "0 43 13 * * *")
	public List<String> getAllEuropeanFrenchSpeakingCountries() throws Throwable {
		CompletableFuture<List<Role>> countriesByLanguageFuture = roleService.getCountriesByLanguage("ROLE_ADMIN");
		CompletableFuture<List<Role>> countriesByRegionFuture = roleService.getCountriesByRegion("ROLE_ABC");
		List<String> europeanFrenchSpeakingCountries;
		try {
			europeanFrenchSpeakingCountries = new ArrayList<>(
					countriesByLanguageFuture.get().stream().map(Role::getName).collect(Collectors.toList()));
			europeanFrenchSpeakingCountries
					.retainAll(countriesByRegionFuture.get().stream().map(Role::getName).collect(Collectors.toList()));
		} catch (Throwable e) {
			throw e.getCause();
		}
		System.out.println(europeanFrenchSpeakingCountries);
		return europeanFrenchSpeakingCountries;
	}
}
