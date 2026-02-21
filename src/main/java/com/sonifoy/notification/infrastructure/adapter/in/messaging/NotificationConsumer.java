package com.sonifoy.notification.infrastructure.adapter.in.messaging;

import com.sonifoy.notification.application.service.NotificationService;
import com.sonifoy.notification.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @Bean
    public Consumer<TransactionEvent> transactionEventConsumer() {
        return event -> {
            log.info("Received transaction event: {}", event);
            try {
                // Determine recipient based on transaction details (simplified logic)
                // In a real scenario, we'd look up the User ID associated with the target
                // Wallet ID via user-service or wallet-service
                String recipientId = "system"; // Placeholder

                String message = String.format("Transaction %s of type %s for amount %s processed.",
                        event.getTransactionId(), event.getType(), event.getAmount());

                notificationService.createNotification(
                        recipientId,
                        "New Transaction",
                        message,
                        Notification.NotificationType.INFO).subscribe();
            } catch (Exception e) {
                log.error("Error processing transaction event", e);
            }
        };
    }
}
