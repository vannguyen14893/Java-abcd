package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.GroupService;

@RestController
@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class GroupController {
	@Autowired
	private GroupService groupService;

	@PostMapping(value = "/add-user-group/{groupId}")
	public void addUserGroup(@PathVariable("groupId") Integer groupId, @RequestBody Integer[] userId) {
		groupService.addUserGroup(groupId, userId);
	}
}
