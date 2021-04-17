package com.navi;

import com.navi.pojo.OrderTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains log of all the transactions done in the system
 */
public class OrderTransactionLedger {
    private final List<OrderTransaction> orderTransactions;

    public OrderTransactionLedger() {
        this.orderTransactions = new ArrayList<>();
    }

    public void addToLedger(OrderTransaction orderTransaction) {
        if (orderTransaction == null) {
            return;
        }
        System.out.println("transaction is executed " + orderTransaction);
        orderTransactions.add(orderTransaction);
    }

    public void printTransactionsInLedger() {
        orderTransactions.forEach(orderTransaction -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#").append(orderTransaction.getDestinationId()).append(" ");
            stringBuilder.append(String.format("%.2f", orderTransaction.getMatchingPrice())).append(" ");
            stringBuilder.append(orderTransaction.getQuantity()).append(" ");
            stringBuilder.append(orderTransaction.getSourceId());
            System.out.println(stringBuilder.toString());
        });
    }
}
