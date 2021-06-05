package pl.zzpj.rest.mappers;

import pl.zzpj.model.Transaction;
import pl.zzpj.dto.TransactionDto;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setFrom(AccountMapper.mapToAccountDto(transaction.getFrom()));
        transactionDto.setTo(AccountMapper.mapToAccountDto(transaction.getTo()));
        transactionDto.setRate(transaction.getRate());

        return transactionDto;
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();

        transaction.setDate(transactionDto.getDate());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setFrom(AccountMapper.mapToAccount(transactionDto.getFrom()));
        transaction.setTo(AccountMapper.mapToAccount(transactionDto.getTo()));
        transaction.setRate(transactionDto.getRate());

        return transaction;
    }
}
