package org.beta.buget_app.service;

import lombok.RequiredArgsConstructor;
import org.beta.buget_app.enums.TransactionType;
import org.beta.buget_app.model.Transaction;
import org.beta.buget_app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public void saveAllTransactions(List<Transaction> transactionList) {
        transactionRepository.saveAll(transactionList);
    }

    public List<Transaction> getTransactionsFiltered(String product, TransactionType type, Double minAmount, Double maxAmount) {
        return transactionRepository.findFilteredTransactions(product, type, minAmount, maxAmount);
    }


    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(id);
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> updateTransaction(int id, Transaction newTransaction) {
        return transactionRepository.findById(id).map(existingTransaction -> transactionRepository.save(newTransaction));
    }

    public boolean deleteTransactionById(int id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Map<TransactionType, List<Transaction>> getTransactionsByType() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getTransactionsByProduct() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}
