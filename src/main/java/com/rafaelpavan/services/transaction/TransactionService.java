package com.rafaelpavan.services.transaction;

import com.rafaelpavan.exceptions.transaction.InvalidTransactionException;
import com.rafaelpavan.models.dtos.transaction.TransactionDto;
import com.rafaelpavan.models.entities.transaction.Transaction;
import com.rafaelpavan.models.entities.user.User;
import com.rafaelpavan.models.enums.user.UserType;
import com.rafaelpavan.models.mapper.transaction.SaveTransactionMapper;
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
     private final SaveTransactionMapper saveTransactionMapper;

    private final UserService userService;


    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository,
                              AuthorizationService authorizationService, NotificationService notificationService, SaveTransactionMapper saveTransactionMapper,
                              UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.saveTransactionMapper = saveTransactionMapper;
        this.userService = userService;
    }


    @Transactional
    public TransactionDto create(TransactionDto transactionDto) {

        User sender = this.userService.findUserById(transactionDto.sender_id());
        User receiver = this.userService.findUserByEmail(transactionDto.receiver_email());

        validate(transactionDto);

        Transaction savedTransaction = transactionRepository.save(saveTransactionMapper.toEntity(transactionDto));
        savedTransaction.setAmount(transactionDto.amount());
        savedTransaction.setSender(sender);
        savedTransaction.setEmail(receiver.getEmail());
        savedTransaction.setTransaction_time(LocalDateTime.now());

        var userSender = userRepository.findById(transactionDto.sender_id()).get();
        var userReceiver = userRepository.findUserByEmail(transactionDto.receiver_email()).get();

        userRepository.save(userSender.debit(transactionDto.amount()));
        userRepository.save(userReceiver.credit(transactionDto.amount()));

        //4 - chamar transação

        authorizationService.authorize(transactionDto);

        // notification
//        notificationService.notify(transactionDto);


        return saveTransactionMapper.toDto(savedTransaction);
    }

    /*
     * rules to validate payer
     * - the payer must be a common user
     * - the payer has enough balance
     * - the payer isn't the payee (transaction to herself)
     * */
    private void validate(TransactionDto transaction) {
        userRepository.findUserByEmail(transaction.receiver_email())
                .map(payee -> userRepository.findById(transaction.sender_id())
                        .map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
                        .orElseThrow(InvalidTransactionException::new))
                .orElseThrow(InvalidTransactionException::new);
    }

    private static boolean isTransactionValid(TransactionDto transaction, User payer) {
        return payer.getUserType() == UserType.COMMON &&
                payer.getBalance().compareTo(transaction.amount()) >= 0 &&
                !payer.getEmail().equals(transaction.receiver_email());
    }

    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return saveTransactionMapper.toListDto(transactionList);
    }
}
