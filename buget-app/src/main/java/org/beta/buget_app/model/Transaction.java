package org.beta.buget_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.beta.buget_app.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String product;
        private TransactionType type;
        private double amount;
}
