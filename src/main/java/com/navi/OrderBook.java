package com.navi;

import com.navi.pojo.Order;

import java.util.List;

/**
 * Exposes methods to register orders submitted to the system
 */
public interface OrderBook {
    void registerOrder(Order order);

    List<Order> getBuyOrders();

    List<Order> getSellOrders();
}
