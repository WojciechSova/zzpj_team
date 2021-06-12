package pl.zzpj.rest.mappers;

import pl.zzpj.model.Transaction;
import pl.zzpj.dto.TransactionDto;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setDate(transaction.getDate());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setFrom(AccountMapper.mapToAccountDto(transaction.getFrom()));
        transactionDto.setFromCurrency(CurrencyMapper.mapToCurrencyDto(transaction.getFromCurrency()));
        transactionDto.setTo(AccountMapper.mapToAccountDto(transaction.getTo()));
        transactionDto.setToCurrency(CurrencyMapper.mapToCurrencyDto(transaction.getToCurrency()));
        transactionDto.setRate(transaction.getRate());

        return transactionDto;
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();

        transaction.setDate(transactionDto.getDate());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setFrom(AccountMapper.mapToAccount(transactionDto.getFrom()));
        transaction.setFromCurrency(CurrencyMapper.mapToCurrency(transactionDto.getFromCurrency()));
        transaction.setTo(AccountMapper.mapToAccount(transactionDto.getTo()));
        transaction.setToCurrency(CurrencyMapper.mapToCurrency(transactionDto.getToCurrency()));
        transaction.setRate(transactionDto.getRate());

        return transaction;
    }
}
