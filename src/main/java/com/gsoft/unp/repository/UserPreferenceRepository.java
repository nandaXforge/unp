package com.gsoft.unp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsoft.unp.entity.UserPreference;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> {

	Optional<UserPreference> findByUserId(String userId);

}
