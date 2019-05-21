package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}
