package com.gsoft.unp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gsoft.unp.entity.NotificationLog;
import com.gsoft.unp.entity.UserPreference;
import com.gsoft.unp.repository.NotificationLogRepository;

@Service
public class NotificationService {

	private final NotificationLogRepository logRepository;
	private final UserPreferenceService preferenceService;

	public NotificationService(NotificationLogRepository logRepository, UserPreferenceService preferenceService) {
		this.logRepository = logRepository;
		this.preferenceService = preferenceService;
	}

	public String sendNotification(String userId, NotificationLog.NotificationType type,
			NotificationLog.Channel channel, String content) {
		UserPreference preference = preferenceService.getPreference(userId);

		// Validate if the user opted in for the type and channel
		if (!preference.getPreferences().getOrDefault(type.name().toLowerCase(), false)
				|| !preference.getChannels().getOrDefault(channel.name().toLowerCase(), false)) {
			throw new RuntimeException("User has opted out of this notification type or channel.");
		}

		NotificationLog log = new NotificationLog();
		log.setUserId(userId);
		log.setType(type);
		log.setChannel(channel);
		log.setStatus(NotificationLog.Status.SENT);
		log.setSentAt(LocalDateTime.now());
		log.setMetadata(Map.of("content", content));
		logRepository.save(log);

		return "Notification sent successfully!";
	}

	public List<NotificationLog> getLogs(String userId) {
		return logRepository.findByUserId(userId);
	}

	public Object getStatistics() {
		long totalNotifications = logRepository.count();
		long sentNotifications = logRepository.findAll().stream()
				.filter(log -> log.getStatus() == NotificationLog.Status.SENT).count();
		long failedNotifications = logRepository.findAll().stream()
				.filter(log -> log.getStatus() == NotificationLog.Status.FAILED).count();

		return Map.of("totalNotifications", totalNotifications, "sentNotifications", sentNotifications,
				"failedNotifications", failedNotifications);
	}
}
