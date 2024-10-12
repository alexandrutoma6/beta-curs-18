package org.beta.buget_app.service;

import lombok.RequiredArgsConstructor;
import org.beta.buget_app.enums.TransactionType;
import org.beta.buget_app.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final List<Transaction> transactionList = new ArrayList<>();

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList.clear();
        this.transactionList.addAll(transactionList);
    }

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public List<Transaction> getTransactionsFiltered(String product, TransactionType type, Double minAmount, Double maxAmount) {
        return transactionList.stream()
                .filter(transaction -> product == null || transaction.product().equals(product))
                .filter(transaction -> type == null || transaction.type().equals(type))
                .filter(transaction -> minAmount == null || transaction.amount() >= minAmount)
                .filter(transaction -> maxAmount == null || transaction.amount() <= maxAmount)
                .collect(Collectors.toList());
    }

    public Optional<Transaction> getTransactionById(int id) {
        return transactionList.stream()
                .filter(transaction -> transaction.id() == id)
                .findFirst();
    }

    public Transaction addTransaction(Transaction transaction) {
        transactionList.add(transaction);
        return transaction;
    }

    public Optional<Transaction> updateTransaction(int id, Transaction newTransaction) {
        return getTransactionById(id).map(existingTransaction -> {
            int index = transactionList.indexOf(existingTransaction);
            transactionList.set(index, newTransaction);
            return newTransaction;
        });
    }

    public boolean deleteTransactionById(int id) {
        return transactionList.removeIf(transaction -> transaction.id() == id);
    }

    public Map<TransactionType, List<Transaction>> getTransactionsByType() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    public Map<String, List<Transaction>> getTransactionsByProduct() {
        return transactionList.stream()
                .collect(Collectors.groupingBy(Transaction::product));
    }
}
