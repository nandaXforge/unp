package com.gsoft.unp.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.gsoft.unp.entity.UserPreference;
import com.gsoft.unp.repository.UserPreferenceRepository;

@Service
public class UserPreferenceService {

	private final UserPreferenceRepository repository;

	public UserPreferenceService(UserPreferenceRepository repository) {
		this.repository = repository;
	}

	public UserPreference createPreference(UserPreference preference) {
		preference.setCreatedAt(LocalDateTime.now());
		preference.setLastUpdated(LocalDateTime.now());
		return repository.save(preference);
	}

	public UserPreference getPreference(String userId) {
		return repository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User preference not found."));
	}

	public UserPreference updatePreference(String userId, UserPreference updates) {
		UserPreference existing = getPreference(userId);
		existing.setPreferences(updates.getPreferences());
		existing.setChannels(updates.getChannels());
		existing.setFrequency(updates.getFrequency());
		existing.setTimezone(updates.getTimezone());
		existing.setLastUpdated(LocalDateTime.now());
		return repository.save(existing);
	}

	public void deletePreference(String userId) {
		UserPreference preference = getPreference(userId);
		repository.delete(preference);
	}
}
