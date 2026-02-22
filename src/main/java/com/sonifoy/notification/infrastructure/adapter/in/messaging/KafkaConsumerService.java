package com.sonifoy.notification.infrastructure.adapter.in.messaging;

import com.sonifoy.notification.infrastructure.adapter.in.messaging.dto.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    @KafkaListener(topics = "user-events", groupId = "notification-service-group")
    public void consumeUserRegisteredEvent(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent from Kafka. Preparing to send welcome notification to email: {}",
                event.getEmail());

        // TODO: Implement actual email sending logic using JavaMailSender or an email
        // provider API like SendGrid/AWS SES.
        log.info("Mock: Welcome email sent successfully to User ID: {} ({})", event.getUserId(), event.getName());
    }
}
