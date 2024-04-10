package com.rafaelpavan.models.dtos.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto (BigDecimal amount, UUID sender_id, UUID receiver_id, String transaction_time) {
}
