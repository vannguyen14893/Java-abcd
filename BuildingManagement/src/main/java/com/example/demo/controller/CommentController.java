package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;
import com.example.demo.service.NotificationService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationService notificationService;

	@GetMapping(value = "/list-comment")
	public List<CommentDto> getComentDto() {
		return commentService.getNodeComment();
	}

	@GetMapping(value = "/save-comment/{content}/{parentId}")
	public CommentDto saveComment(@PathVariable("content") String content, @PathVariable("parentId") Integer parentId) {
		return commentService.saveComment(content, parentId);
	}

	@PostMapping(value = "/add-comment")
	public void addComment(@RequestBody Comment comment) {
		User user = userRepository.findOneByUserId(1);
		User receiver = userRepository.findOneByUserId(2);
		commentService.addCommment(comment);
		Notification notification = new Notification();
		notification.setContent("comment");
		notificationService.sendNotification(notification, user, comment, receiver);
	}
}