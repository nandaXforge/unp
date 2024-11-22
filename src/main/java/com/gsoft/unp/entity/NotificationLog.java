package com.gsoft.unp.entity;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String userId;

	@Enumerated(EnumType.STRING)
	private NotificationType type;

	@Enumerated(EnumType.STRING)
	private Channel channel;

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDateTime sentAt;
	
	private String failureReason;

	@ElementCollection
	private Map<String, Object> metadata;

	public enum NotificationType {
		MARKETING, NEWSLETTER, UPDATES
	}

	public enum Channel {
		EMAIL, SMS, PUSH
	}

	public enum Status {
		PENDING, SENT, FAILED
	}

	// Getters and setters
}
