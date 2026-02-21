package com.sonifoy.notification.infrastructure.adapter.in.web;

import com.sonifoy.notification.application.service.NotificationService;
import com.sonifoy.notification.domain.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public Flux<Notification> getUserNotifications(@PathVariable String userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Notification> createNotification(@RequestBody CreateNotificationRequest request) {
        return notificationService.createNotification(
                request.getRecipientId(),
                request.getTitle(),
                request.getMessage(),
                Notification.NotificationType.valueOf(request.getType()));
    }

    @PutMapping("/{id}/read")
    public Mono<Void> markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateNotificationRequest {
        private String recipientId;
        private String title;
        private String message;
        private String type;
    }
}
