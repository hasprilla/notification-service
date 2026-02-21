package com.sonifoy.notification.application.service;

import com.sonifoy.notification.domain.model.Notification;
import com.sonifoy.notification.infrastructure.adapter.out.persistence.NotificationEntity;
import com.sonifoy.notification.infrastructure.adapter.out.persistence.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Flux<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByRecipientIdOrderByCreatedAtDesc(userId)
                .map(this::mapToDomain);
    }

    public Mono<Notification> createNotification(String recipientId, String title, String message,
            Notification.NotificationType type) {
        NotificationEntity entity = NotificationEntity.builder()
                .recipientId(recipientId)
                .title(title)
                .message(message)
                .type(type.name())
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .build();

        return notificationRepository.save(entity)
                .map(this::mapToDomain);
    }

    public Mono<Void> markAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .flatMap(notification -> {
                    notification.setRead(true);
                    return notificationRepository.save(notification);
                })
                .then();
    }

    private Notification mapToDomain(NotificationEntity entity) {
        return Notification.builder()
                .id(entity.getId())
                .recipientId(entity.getRecipientId())
                .title(entity.getTitle())
                .message(entity.getMessage())
                .type(Notification.NotificationType.valueOf(entity.getType()))
                .read(entity.isRead())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
