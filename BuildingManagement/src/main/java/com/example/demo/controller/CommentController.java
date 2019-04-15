package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CommentDto;
import com.example.demo.service.CommentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping(value = "/list-comment")
	public List<CommentDto> getComentDto() {
		return commentService.getNodeComment();
	}
	@GetMapping(value = "/save-comment/{content}/{parentId}")
	public CommentDto saveComment(@PathVariable("content") String content,@PathVariable("parentId") Integer parentId ) {
		return commentService.saveComment(content, parentId);
	}
}