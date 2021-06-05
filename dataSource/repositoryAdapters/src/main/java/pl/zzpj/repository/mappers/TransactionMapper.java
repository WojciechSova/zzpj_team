package pl.zzpj.repository.mappers;

import pl.zzpj.model.Transaction;

import pl.zzpj.entities.TransactionEnt;
import pl.zzpj.entities.AccountEnt;

public class TransactionMapper {

    public static TransactionEnt mapToTransactionEnt(Transaction transaction) {
        TransactionEnt transactionEnt = new TransactionEnt();

        transactionEnt.setDate(transaction.getDate());
        transactionEnt.setAmount(transaction.getAmount());
        transactionEnt.setFrom((AccountEnt) AccountMapper.mapToAccountEnt(transaction.getFrom()));
        transactionEnt.setTo((AccountEnt) AccountMapper.mapToAccountEnt(transaction.getTo()));
        transactionEnt.setRate(transaction.getRate());

        return transactionEnt;
    }

    public static Transaction mapToTransaction(TransactionEnt transactionEnt) {
        Transaction transaction = new Transaction();

        transaction.setDate(transactionEnt.getDate());
        transaction.setAmount(transactionEnt.getAmount());
        transaction.setFrom(AccountMapper.mapToAccount(transactionEnt.getFrom()));
        transaction.setTo(AccountMapper.mapToAccount(transactionEnt.getTo()));
        transaction.setRate(transactionEnt.getRate());

        return transaction;
    }
}
