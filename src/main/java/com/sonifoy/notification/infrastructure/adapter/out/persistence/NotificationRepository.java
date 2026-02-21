package com.sonifoy.notification.infrastructure.adapter.out.persistence;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface NotificationRepository extends R2dbcRepository<NotificationEntity, Long> {
    Flux<NotificationEntity> findByRecipientIdOrderByCreatedAtDesc(String recipientId);
}
