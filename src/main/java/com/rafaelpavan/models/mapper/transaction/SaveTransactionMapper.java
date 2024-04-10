package com.rafaelpavan.models.mapper.transaction;

import com.rafaelpavan.models.dtos.transaction.TransactionDto;
import com.rafaelpavan.models.entities.transaction.Transaction;
import com.rafaelpavan.models.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SaveTransactionMapper {

    Transaction toEntity (TransactionDto toDto);

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "sender", target = "sender_id")
    @Mapping(source = "receiver", target = "receiver_id")
    @Mapping(source = "transaction_time", target = "transaction_time")
    TransactionDto toDto(Transaction entity);

    List<TransactionDto> toListDto(List<Transaction> entities);

    default UUID mapUserToUUID(User user) {
        return user != null ? user.getId() : null;
    }
}
