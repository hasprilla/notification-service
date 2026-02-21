package com.sonifoy.notification.infrastructure.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("notifications")
public class NotificationEntity {
    @Id
    private Long id;
    private String recipientId;
    private String title;
    private String message;
    private String type;
    private boolean isRead; // 'read' is reserved keyword in some DBs
    private LocalDateTime createdAt;
}
