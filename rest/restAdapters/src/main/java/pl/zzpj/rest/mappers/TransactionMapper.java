package pl.zzpj.rest.mappers;

import pl.zzpj.model.Transaction;
import pl.zzpj.modelDto.TransactionDto;
import pl.zzpj.modelDto.usersDto.ClientDto;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setFrom((ClientDto) AccountMapper.mapToAccountDto(transaction.getFrom()));
        transactionDto.setTo((ClientDto) AccountMapper.mapToAccountDto(transaction.getTo()));

        return transactionDto;
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();

        transaction.setDate(transactionDto.getDate());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setFrom(AccountMapper.mapToAccount(transactionDto.getFrom()));
        transaction.setTo(AccountMapper.mapToAccount(transactionDto.getTo()));

        return transaction;
    }
}