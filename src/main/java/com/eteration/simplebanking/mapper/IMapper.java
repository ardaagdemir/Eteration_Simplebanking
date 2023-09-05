package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.response.CreateAccountResponseDto;
import com.eteration.simplebanking.dto.response.TransactionResponseDto;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapper {
    IMapper INSTANCE = Mappers.getMapper(IMapper.class);

    List<TransactionResponseDto> fromTransactionToTransactionResponseDto(List<Transaction> transaction);
}
