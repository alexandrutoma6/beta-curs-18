package org.beta.buget_app.repository;

import org.beta.buget_app.enums.TransactionType;
import org.beta.buget_app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE (:product IS NULL OR t.product = :product) " +
            "AND (:type IS NULL OR t.type = :type) " +
            "AND (:minAmount IS NULL OR t.amount >= :minAmount) " +
            "AND (:maxAmount IS NULL OR t.amount <= :maxAmount)")
    List<Transaction> findFilteredTransactions(@Param("product") String product,
                                               @Param("type") TransactionType type,
                                               @Param("minAmount") Double minAmount,
                                               @Param("maxAmount") Double maxAmount);
}
