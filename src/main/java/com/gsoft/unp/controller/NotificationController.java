package com.gsoft.unp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsoft.unp.entity.NotificationLog;
import com.gsoft.unp.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	private final NotificationService service;

	public NotificationController(NotificationService service) {
		this.service = service;
	}

	@PostMapping("/send")
	public ResponseEntity<String> sendNotification(@RequestParam String userId,
			@RequestParam NotificationLog.NotificationType type, @RequestParam NotificationLog.Channel channel,
			@RequestBody String content) {
		return ResponseEntity.ok(service.sendNotification(userId, type, channel, content));
	}

	@GetMapping("/{userId}/logs")
	public ResponseEntity<List<NotificationLog>> getLogs(@PathVariable String userId) {
		return ResponseEntity.ok(service.getLogs(userId));
	}

	@GetMapping("/stats")
	public ResponseEntity<Object> getStatistics() {
		return ResponseEntity.ok(service.getStatistics());
	}
}
