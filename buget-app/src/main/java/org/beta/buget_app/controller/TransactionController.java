package org.beta.buget_app.controller;

import lombok.RequiredArgsConstructor;
import org.beta.buget_app.enums.TransactionType;
import org.beta.buget_app.model.Transaction;
import org.beta.buget_app.service.TransactionService;
import org.beta.buget_app.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {
        return service.getTransactionsFiltered(product, type, minAmount, maxAmount);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return service.getTransactionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found for id: " + id));
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.addTransaction(transaction);
    }

    @PutMapping("/transactions/{id}")
    public Transaction updateTransaction(@PathVariable int id,
                                         @RequestBody Transaction transaction) {
        return service.updateTransaction(id, transaction)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found for id: " + id));
    }

    @DeleteMapping("/transactions/{id}")
    public String deleteTransaction(@PathVariable int id) {
        if (!service.deleteTransactionById(id)) {
            throw new ResourceNotFoundException("Transaction not found for id: " + id);
        }
        return "Transaction deleted successfully";
    }

    @GetMapping("/transactions/reports/type")
    public Map<TransactionType, List<Transaction>> getTransactionsByType() {
        return service.getTransactionsByType();
    }

    @GetMapping("/transactions/reports/product")
    public Map<String, List<Transaction>> getTransactionsByProduct() {
        return service.getTransactionsByProduct();
    }
}
