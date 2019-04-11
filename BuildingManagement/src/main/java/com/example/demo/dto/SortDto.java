package com.example.demo.dto;

import java.io.Serializable;

public class SortDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileName;
	private Boolean sort;
	private String status;
	private String name;

	public SortDto(String fileName, Boolean sort) {
		super();
		this.fileName = fileName;
		this.sort = sort;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getSort() {
		return sort;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRoleName(String name) {
		this.name = name;
	}

	public SortDto() {
		super();
	}

}
