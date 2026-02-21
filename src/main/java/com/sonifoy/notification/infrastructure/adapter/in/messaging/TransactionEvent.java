package com.sonifoy.notification.infrastructure.adapter.in.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent {
    private String transactionId;
    private Long sourceWalletId;
    private Long targetWalletId;
    private BigDecimal amount;
    private String type;
    private LocalDateTime timestamp;
}
