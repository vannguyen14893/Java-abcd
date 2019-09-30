package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	@Query("select r from Role r where r.name =?1")
	List<Role> findByName(String name);

}
