package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Notification;
import com.example.demo.entity.User;
import com.example.demo.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {
	@Autowired
	private NotificationRepository repository;
    @Autowired
	private SimpMessagingTemplate template;

	public void sendNotification(Notification notification, User user, Comment comment, User receiver) {
		notification.setUser(user);
		notification.setReceiver(receiver);
		notification.setComment(comment);
		notification.setCreateDate(new Date());
		template.convertAndSend("/chat/" + receiver.getUserName(), notification);
		repository.save(notification);
	}
}
