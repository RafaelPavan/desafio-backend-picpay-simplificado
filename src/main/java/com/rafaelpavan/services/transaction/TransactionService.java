package com.rafaelpavan.services.transaction;

import com.rafaelpavan.exceptions.transaction.InvalidTransactionException;
import com.rafaelpavan.models.dtos.transaction.TransactionDto;
import com.rafaelpavan.models.entities.transaction.Transaction;
import com.rafaelpavan.models.entities.user.User;
import com.rafaelpavan.models.enums.user.UserType;
import com.rafaelpavan.repositories.transaction.TransactionRepository;
import com.rafaelpavan.repositories.user.UserRepository;
import com.rafaelpavan.services.authorization.AuthorizationService;
import com.rafaelpavan.services.notification.NotificationService;
import com.rafaelpavan.services.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AuthorizationService authorizationService, NotificationService notificationService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @Transactional
    public Transaction create(TransactionDto transaction) {

        User sender = this.userService.findUserById(transaction.sender());
        User receiver = this.userService.findUserById(transaction.receiver());

        //1 - Validar
        validate(transaction);

        //2 - Criar transação
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTransaction_time(LocalDateTime.now());


        //3 - debitar da carteira
        var userSender = userRepository.findById(transaction.sender());
        var userReceiver = userRepository.findById(transaction.receiver());
        userRepository.save(userSender.get().debit(transaction.amount()));
        userRepository.save(userReceiver.get().credit(transaction.amount()));

        this.transactionRepository.save(newTransaction);


        //4 - chamar transação
            // authorize transaction
        authorizationService.authorize(transaction);

        // notification
//        notificationService.notify(transaction);
        return newTransaction;
    }

    /*
     * rules to validate payer
     * - the payer must be a common user
     * - the payer has enough balance
     * - the payer isn't the payee (transaction to herself)
     * */
    private void validate(TransactionDto transaction) {
        userRepository.findById(transaction.receiver())
                .map(payee -> userRepository.findById(transaction.sender())
                        .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(InvalidTransactionException::new))
                .orElseThrow(InvalidTransactionException::new);

    }

    private static boolean isTransactionValid(TransactionDto transaction, User payer) {
        return payer.getUserType() == UserType.COMMON &&
                payer.getBalance().compareTo(transaction.amount()) >= 0 &&
                !payer.getId().equals(transaction.receiver());
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}
