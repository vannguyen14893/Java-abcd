package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class GroupService {
	@Autowired
	private GroupRepository groupRepository;
	@Autowired
	private UserRepository userRepository;

	public void addUserGroup(Integer groupId, Integer[] userId) {
		Group group = groupRepository.findById(groupId).get();
		group.getUsers().clear();
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < userId.length; i++) {
			users.add(userRepository.getOne(userId[i]));

		}
		group.setUsers(users);
		groupRepository.save(group);
	}
}
