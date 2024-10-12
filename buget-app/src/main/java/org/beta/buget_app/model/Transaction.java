package org.beta.buget_app.model;

import org.beta.buget_app.enums.TransactionType;

public record Transaction(
        int id,
        String product,
        TransactionType type,
        double amount) {
}
