package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {
	private Integer commentId;
	private String content;
	private Integer status;
	private Integer parentId;
	private List<CommentDto> commentDtos = new ArrayList<>();
	private String nameUser;

	public CommentDto() {
		super();
	}

	
	
	public CommentDto(Integer commentId, String content, Integer status, Integer parentId, String nameUser) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.status = status;
		this.parentId = parentId;
		this.nameUser = nameUser;
	}



	public CommentDto(Integer commentId, String content, Integer status, Integer parentId, List<CommentDto> commentDtos,
			String nameUser) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.status = status;
		this.parentId = parentId;
		this.commentDtos = commentDtos;
		this.nameUser = nameUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentDtos == null) ? 0 : commentDtos.hashCode());
		result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((nameUser == null) ? 0 : nameUser.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDto other = (CommentDto) obj;
		if (commentDtos == null) {
			if (other.commentDtos != null)
				return false;
		} else if (!commentDtos.equals(other.commentDtos))
			return false;
		if (commentId == null) {
			if (other.commentId != null)
				return false;
		} else if (!commentId.equals(other.commentId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public List<CommentDto> getCommentDtos() {
		return commentDtos;
	}

	public void setCommentDtos(List<CommentDto> commentDtos) {
		this.commentDtos = commentDtos;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

}