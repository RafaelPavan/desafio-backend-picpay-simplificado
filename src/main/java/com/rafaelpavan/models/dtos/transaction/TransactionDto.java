package com.rafaelpavan.models.dtos.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto (BigDecimal amount, UUID sender, UUID receiver, String transaction_time) {
}
