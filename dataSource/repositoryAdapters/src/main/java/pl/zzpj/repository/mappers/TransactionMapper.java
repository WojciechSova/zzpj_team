package pl.zzpj.repository.mappers;

import pl.zzpj.model.Transaction;

import pl.zzpj.entities.TransactionEnt;
import pl.zzpj.entities.AccountEnt;

public class TransactionMapper {

    public static TransactionEnt mapToTransactionEnt(Transaction transaction) {
        TransactionEnt transactionEnt = new TransactionEnt();

        transactionEnt.setDate(transaction.getDate());
        transactionEnt.setAmount(transaction.getAmount());
        transactionEnt.setFromId((AccountEnt) AccountMapper.mapToAccountEnt(transaction.getFrom()));
        transactionEnt.setFromCurrency(CurrencyMapper.mapToCurrencyEnt(transaction.getFromCurrency()));
        transactionEnt.setToId((AccountEnt) AccountMapper.mapToAccountEnt(transaction.getTo()));
        transactionEnt.setToCurrency(CurrencyMapper.mapToCurrencyEnt(transaction.getToCurrency()));
        transactionEnt.setRate(transaction.getRate());

        return transactionEnt;
    }

    public static Transaction mapToTransaction(TransactionEnt transactionEnt) {
        Transaction transaction = new Transaction();

        transaction.setDate(transactionEnt.getDate());
        transaction.setAmount(transactionEnt.getAmount());
        transaction.setFrom(AccountMapper.mapToAccount(transactionEnt.getFromId()));
        transaction.setFromCurrency(CurrencyMapper.mapToCurrency(transactionEnt.getFromCurrency()));
        transaction.setTo(AccountMapper.mapToAccount(transactionEnt.getToId()));
        transaction.setToCurrency(CurrencyMapper.mapToCurrency(transactionEnt.getToCurrency()));
        transaction.setRate(transactionEnt.getRate());

        return transaction;
    }
}
