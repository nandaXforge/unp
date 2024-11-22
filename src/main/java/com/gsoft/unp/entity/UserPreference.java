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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String userId;

    @NotBlank
    @Email
    private String email;

    @ElementCollection
    private Map<String, Boolean> preferences; // e.g., {"marketing": true, "newsletter": false}

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @ElementCollection
    private Map<String, Boolean> channels; // e.g., {"email": true, "sms": false}

    @NotBlank
    private String timezone;

    private LocalDateTime lastUpdated;
    private LocalDateTime createdAt;

    public enum Frequency {
        DAILY, WEEKLY, MONTHLY, NEVER
    }

}
