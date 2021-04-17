package com.navi;

import com.navi.pojo.Order;
import com.navi.pojo.OrderTransaction;
import com.navi.pojo.OrderType;

import java.util.List;

public class OrderMatcherRealTime {
    private final OrderBookImpl orderBook;
    private final OrderTransactionLedger transactionLedger;

    public OrderMatcherRealTime(OrderBookImpl orderBook, OrderTransactionLedger transactionLedger) {
        this.orderBook = orderBook;
        this.transactionLedger = transactionLedger;
    }

    public void checkForMatchingSellOrder(Order order) {
        Order sellOrder = null;
        for (Order value : orderBook.getSellOrders()) {
            sellOrder = value;
            if (sellOrder.getAmount() <= order.getAmount()) {
                OrderTransaction orderTransaction = new OrderTransaction();
                orderTransaction.setSourceId(sellOrder.getId());
                orderTransaction.setDestinationId(order.getId());
                orderTransaction.setMatchingPrice(sellOrder.getAmount());
                orderTransaction.setQuantity(Math.min(sellOrder.getQuantity(), order.getQuantity()));
                transactionLedger.addToLedger(orderTransaction);
                break;
            }
        }

        if (sellOrder == null) {
            return;
        }
        updateOrderDetails(order, sellOrder, orderBook.getSellOrders());
    }

    public void checkForMatchingBuyOrder(Order order) {
        Order buyOrder = null;
        for (Order value : orderBook.getBuyOrders()) {
            buyOrder = value;
            if (buyOrder.getAmount() >= order.getAmount()) {
                OrderTransaction orderTransaction = new OrderTransaction();
                orderTransaction.setSourceId(order.getId());
                orderTransaction.setDestinationId(buyOrder.getId());
                orderTransaction.setMatchingPrice(order.getAmount());
                orderTransaction.setQuantity(Math.min(buyOrder.getQuantity(), order.getQuantity()));
                transactionLedger.addToLedger(orderTransaction);
                break;
            }
        }
        if (buyOrder == null) {
            return;
        }
        updateOrderDetails(order, buyOrder, orderBook.getBuyOrders());
    }

    private void updateOrderDetails(Order order, Order matchingOrder, List<Order> ordersList) {
        if (matchingOrder.getQuantity() == order.getQuantity()) {
            ordersList.remove(matchingOrder);
        } else if (matchingOrder.getQuantity() > order.getQuantity()) {
            matchingOrder.setQuantity(matchingOrder.getQuantity() - order.getQuantity());
        } else {
            ordersList.remove(matchingOrder);
            order.setQuantity(order.getQuantity() - matchingOrder.getQuantity());
            if (OrderType.BUY.equals(order.getOrderType())) {
                checkForMatchingSellOrder(order);
            } else {
                checkForMatchingBuyOrder(order);
            }
        }
    }
}

