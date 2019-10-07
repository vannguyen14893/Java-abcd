package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ConfigItem;

@Repository
public interface ConfigRepo extends JpaRepository<ConfigItem, String> {
	ConfigItem findByConfigKey(String key);
}
