//package org.beta.buget_app;
//
//import org.beta.buget_app.enums.TransactionType;
//import org.beta.buget_app.model.Transaction;
//import org.beta.buget_app.service.TransactionService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TransactionServiceTests {
//
//    private TransactionService transactionService;
//
//    private List<Transaction> transactionList;
//
//    @BeforeEach
//    public void setup() {
//        transactionList = List.of(
//                new Transaction(1, "Product A", TransactionType.BUY, 100.0),
//                new Transaction(2, "Product B", TransactionType.SELL, 50.0),
//                new Transaction(3, "Product C", TransactionType.BUY, 200.0),
//                new Transaction(4, "Product D", TransactionType.SELL, 75.0),
//                new Transaction(5, "Product E", TransactionType.BUY, 150.0),
//                new Transaction(6, "Product A", TransactionType.SELL, 25.0),
//                new Transaction(7, "Product B", TransactionType.BUY, 300.0),
//                new Transaction(8, "Product C", TransactionType.SELL, 120.0),
//                new Transaction(9, "Product C", TransactionType.BUY, 90.0),
//                new Transaction(10, "Product A", TransactionType.SELL, 60.0)
//        );
//
//        transactionService = new TransactionService();
//        transactionService.setTransactionList(transactionList);
//    }
//
//    @Test
//    @DisplayName("GET ALL TRANSACTIONS")
//    public void testGetTransactions() {
//        List<Transaction> transactions = transactionService.getTransactions();
//
//        assertEquals(10, transactions.size());
//        assertTrue(transactions.containsAll(transactionList));
//    }
//
//    @Test
//    @DisplayName("GET FILTERED TRANSACTIONS")
//    public void testGetTransactionsFiltered() {
//        String product = "Product A";
//        TransactionType type = TransactionType.BUY;
//        Double minAmount = 50.0;
//        Double maxAmount = 150.0;
//
//        List<Transaction> filteredTransactions = transactionService.getTransactionsFiltered(product, type, minAmount, maxAmount);
//
//        assertEquals(1, filteredTransactions.size());
//        assertEquals("Product A", filteredTransactions.get(0).product());
//        assertEquals(TransactionType.BUY, filteredTransactions.get(0).type());
//        assertEquals(100.0, filteredTransactions.get(0).amount());
//    }
//
//    @Test
//    @DisplayName("GET TRANSACTION BY ID")
//    public void testGetTransactionById() {
//        Optional<Transaction> transaction = transactionService.getTransactionById(3);
//
//        assertTrue(transaction.isPresent());
//        assertEquals(3, transaction.get().id());
//        assertEquals("Product C", transaction.get().product());
//    }
//
//    @Test
//    @DisplayName("ADD TRANSACTION")
//    public void testAddTransaction() {
//        Transaction newTransaction = new Transaction(11, "Product F", TransactionType.SELL, 200.0);
//
//        transactionService.addTransaction(newTransaction);
//        List<Transaction> transactions = transactionService.getTransactions();
//
//        assertEquals(11, transactions.size());
//        assertTrue(transactions.contains(newTransaction));
//    }
//
//    @Test
//    @DisplayName("UPDATE TRANSACTION")
//    public void testUpdateTransaction() {
//        Transaction updatedTransaction = new Transaction(3, "Product C", TransactionType.SELL, 250.0);
//
//        Optional<Transaction> result = transactionService.updateTransaction(3, updatedTransaction);
//        List<Transaction> transactions = transactionService.getTransactions();
//
//        assertTrue(result.isPresent());
//        assertEquals(updatedTransaction, result.get());
//        assertTrue(transactions.contains(updatedTransaction));
//    }
//
//    @Test
//    @DisplayName("DELETE TRANSACTION BY ID")
//    public void testDeleteTransactionById() {
//        boolean isDeleted = transactionService.deleteTransactionById(3);
//        List<Transaction> transactions = transactionService.getTransactions();
//
//        assertTrue(isDeleted);
//        assertEquals(9, transactions.size());
//        assertFalse(transactions.stream().anyMatch(t -> t.id() == 3));
//    }
//
//    @Test
//    @DisplayName("GET TRANSACTIONS BY TYPE")
//    public void testGetTransactionsByType() {
//        Map<TransactionType, List<Transaction>> transactionsByType = transactionService.getTransactionsByType();
//
//        assertEquals(2, transactionsByType.size());
//        assertEquals(5, transactionsByType.get(TransactionType.BUY).size());
//        assertEquals(5, transactionsByType.get(TransactionType.SELL).size());
//    }
//
//    @Test
//    @DisplayName("GET TRANSACTIONS BY PRODUCT")
//    public void testGetTransactionsByProduct() {
//        Map<String, List<Transaction>> transactionsByProduct = transactionService.getTransactionsByProduct();
//
//        assertEquals(5, transactionsByProduct.size());
//        assertEquals(3, transactionsByProduct.get("Product A").size());
//        assertEquals(2, transactionsByProduct.get("Product B").size());
//        assertEquals(3, transactionsByProduct.get("Product C").size());
//    }
//}
