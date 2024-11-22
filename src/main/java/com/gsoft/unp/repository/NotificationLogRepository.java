package com.gsoft.unp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsoft.unp.entity.NotificationLog;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Integer> {

	List<NotificationLog> findByUserId(String userId);

}
