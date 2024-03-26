package com.rafaelpavan.controller.transaction;

import com.rafaelpavan.models.dtos.transaction.TransactionDto;
import com.rafaelpavan.models.entities.transaction.Transaction;
import com.rafaelpavan.services.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody TransactionDto transaction){
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<Transaction> list(){
        return transactionService.getAllTransactions();
    }
}
