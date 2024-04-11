package com.rafaelpavan.models.dtos.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto (BigDecimal amount, UUID sender_id, String receiver_email, String transaction_time) {
}
