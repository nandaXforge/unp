package com.gsoft.unp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsoft.unp.entity.UserPreference;
import com.gsoft.unp.service.UserPreferenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/preferences")
public class UserPreferenceController {

	private final UserPreferenceService service;

	public UserPreferenceController(UserPreferenceService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<UserPreference> createPreference(@Valid @RequestBody UserPreference preference) {
		return ResponseEntity.ok(service.createPreference(preference));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserPreference> getPreference(@PathVariable String userId) {
		return ResponseEntity.ok(service.getPreference(userId));
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<UserPreference> updatePreference(@PathVariable String userId,
			@RequestBody UserPreference updates) {
		return ResponseEntity.ok(service.updatePreference(userId, updates));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deletePreference(@PathVariable String userId) {
		service.deletePreference(userId);
		return ResponseEntity.noContent().build();
	}
}
