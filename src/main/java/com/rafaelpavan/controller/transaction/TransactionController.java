package com.rafaelpavan.controller.transaction;

import com.rafaelpavan.models.dtos.transaction.TransactionDto;
import com.rafaelpavan.services.transaction.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionDto createTransaction(@RequestBody TransactionDto transaction){
        return transactionService.create(transaction);
    }

    @GetMapping
    public List<TransactionDto> list(){
        return transactionService.getAllTransactions();
    }
}
