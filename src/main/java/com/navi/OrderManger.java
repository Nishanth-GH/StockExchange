package com.navi;

import com.navi.pojo.Order;
import com.navi.pojo.OrderType;

/**
 * Directly talks with Cli layer, validates input and executes business logic
 */
public class OrderManger {
    private final OrderBookImpl orderBookImpl;
    private final OrderMatcherRealTime orderMatcher;

    public OrderManger(OrderBookImpl orderBookImpl, OrderMatcherRealTime orderMatcher) {
        this.orderBookImpl = orderBookImpl;
        this.orderMatcher = orderMatcher;
    }

    public void createOrder(Order order) throws Exception {
        validateOrder(order);
        orderBookImpl.registerOrder(order);
        if (OrderType.BUY.equals(order.getOrderType())) {
            orderMatcher.checkForMatchingSellOrder(order);
        } else {
            orderMatcher.checkForMatchingBuyOrder(order);
        }
    }

    public void validateOrder(Order order) throws Exception {
        if (order == null || order.getId() <= 0 || order.getQuantity() <= 0) {
            throw new Exception("Invalid input data");
        }
    }
}
