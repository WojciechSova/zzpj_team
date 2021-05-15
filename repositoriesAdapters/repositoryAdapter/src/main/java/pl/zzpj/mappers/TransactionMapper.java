package pl.zzpj.mappers;

import pl.zzpj.model.Transaction;

import pl.zzpj.modelEnt.TransactionEnt;
import pl.zzpj.modelEnt.usersEnt.ClientEnt;

public class TransactionMapper {

    public static TransactionEnt mapToTransactionEnt(Transaction transaction) {
        TransactionEnt transactionEnt = new TransactionEnt();

        transactionEnt.setDate(transaction.getDate());
        transactionEnt.setAmount(transaction.getAmount());
        transactionEnt.setFrom((ClientEnt) AccountMapper.mapToAccountEnt(transaction.getFrom()));
        transactionEnt.setTo((ClientEnt) AccountMapper.mapToAccountEnt(transaction.getTo()));

        return transactionEnt;
    }

    public static Transaction mapToTransaction(TransactionEnt transactionEnt) {
        Transaction transaction = new Transaction();

        transaction.setDate(transactionEnt.getDate());
        transaction.setAmount(transactionEnt.getAmount());
        transaction.setFrom(AccountMapper.mapToAccount(transactionEnt.getFrom()));
        transaction.setTo(AccountMapper.mapToAccount(transactionEnt.getTo()));

        return transaction;
    }
}
