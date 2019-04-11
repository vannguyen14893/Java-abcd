package com.example.demo.repository.custom;

import java.util.List;

import com.example.demo.dto.SortDto;
import com.example.demo.dto.UserDto;

public interface UserRepositoryCustom {

	List<UserDto> getAllUser(SortDto sortDto);

}
