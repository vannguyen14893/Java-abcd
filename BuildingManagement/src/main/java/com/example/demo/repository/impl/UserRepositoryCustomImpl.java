package com.example.demo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.SortDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.custom.UserRepositoryCustom;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	private static final String query = " SELECT u.userId,u.userName,u.password,u.fullName,u.email,u.phone,u.status FROM User u ";

	private static final String sort = " ORDER BY ";

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public List<UserDto> getAllUser(SortDto sortDto) {
		StringBuilder builder = new StringBuilder(query);

		if (StringUtils.isNotBlank(sortDto.getName())) {
			builder.append("join u.roles r");
		}

		if (StringUtils.isNotBlank(sortDto.getStatus())) {
			builder.append(" AND u.status = '" + sortDto.getStatus() + "'");
		}
		builder.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(sortDto.getName())) {
			builder.append(" AND r.name = '" + sortDto.getName() + "'");
		}

		if (!StringUtils.isNotBlank(sortDto.getFileName())) {
			builder.append(this.sort + "u.userId ASC ");

		} else {
			builder.append(this.sort + "u." + sortDto.getFileName() + "");
			builder.append(sortDto.getSort() ? " ASC" : " DESC");

		}

		List<Object[]> list = entityManager.createQuery(builder.toString()).getResultList();
		List<UserDto> dtos = new ArrayList<UserDto>();

		for (Object[] result : list) {
			UserDto dto = new UserDto();
			dto.setUserId(Integer.parseInt(result[0].toString()));
			dto.setUserName(result[1].toString());
			dto.setPassword(result[2].toString());
			dto.setFullName(result[3].toString());
			dto.setEmail(result[4].toString());
			dto.setPhone(result[5].toString());
			dto.setStatus(Integer.parseInt(result[6].toString()));

			dtos.add(dto);
		}
		return dtos;
	}
}
