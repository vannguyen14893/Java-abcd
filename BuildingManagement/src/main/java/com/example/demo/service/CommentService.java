package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private UserRepository userRepository;

	public List<CommentDto> getNodeComment() {

		List<CommentDto> commentDtos = new ArrayList<CommentDto>();
		List<Comment> comments = commentRepository.findByParentId(0);
		for (Comment item : comments) {
			getNode(commentDtos, item);
		}
		return commentDtos;

	}

	private void getNode(List<CommentDto> commentList, Comment comment) {

		CommentDto commentDto = new CommentDto();
		commentDto.setContent(comment.getContent());
		commentDto.setCommentId(comment.getCommentId());
		// commentDto.setNameUser(comment.getUser().getFullName());
		commentDto.setParentId(comment.getParentId());
		commentDto.setStatus(comment.getStatus());

		commentList.add(commentDto);
		List<Comment> comments = commentRepository.findByParentId(comment.getCommentId());
		for (Comment item : comments) {
			getNode(commentDto.getCommentDtos(), item);
		}

	}

	public CommentDto saveComment(String content, Integer parentID) {

		Comment comment = new Comment();
		comment.setContent(content);
		comment.setUser(userRepository.findOneByUserId(56));
		comment.setStatus(1);
		if (parentID != null) {
			comment.setParentId(parentID);
		} else {
			comment.setParentId(0);
		}
		Comment comment2 = commentRepository.save(comment);
		CommentDto commentDto = null;// new CommentDto(comment2.getCommentId(), comment2.getContent(),
										// comment2.getStatus(), comment2.getParentId(), );
		return commentDto;

	}

	public void addCommment(Comment comment) {
		commentRepository.save(comment);
	}
}