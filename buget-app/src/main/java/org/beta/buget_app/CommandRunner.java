package org.beta.buget_app;

import lombok.RequiredArgsConstructor;
import org.beta.buget_app.enums.TransactionType;
import org.beta.buget_app.model.Transaction;
import org.beta.buget_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandRunner implements CommandLineRunner {

    private final TransactionService transactionService;

    @Override
    public void run(String... args) throws Exception {

        List<Transaction> transactionList = List.of(
                new Transaction(1, "Product A", TransactionType.BUY, 100.0),
                new Transaction(2, "Product B", TransactionType.SELL, 50.0),
                new Transaction(3, "Product C", TransactionType.BUY, 200.0),
                new Transaction(4, "Product D", TransactionType.SELL, 75.0),
                new Transaction(5, "Product E", TransactionType.BUY, 150.0),
                new Transaction(6, "Product A", TransactionType.SELL, 25.0),
                new Transaction(7, "Product B", TransactionType.BUY, 300.0),
                new Transaction(8, "Product C", TransactionType.SELL, 120.0),
                new Transaction(9, "Product C", TransactionType.BUY, 90.0),
                new Transaction(10, "Product A", TransactionType.SELL, 60.0)
        );

        transactionService.setTransactionList(transactionList);

        // Optionally print the transactions to the console
        transactionService.getTransactions().forEach(System.out::println);
    }
}
