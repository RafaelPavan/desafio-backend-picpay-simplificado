package com.rafaelpavan.repositories.transaction;

import com.rafaelpavan.models.entities.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
