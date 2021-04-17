package com.navi;

import com.navi.pojo.Order;
import com.navi.pojo.OrderType;

import java.util.ArrayList;
import java.util.List;

public class OrderBookImpl implements OrderBook {
    private final List<Order> sellOrders;
    private final List<Order> buyOrders;

    public OrderBookImpl() {
        this.sellOrders = new ArrayList<>();
        this.buyOrders = new ArrayList<>();
    }

    public void registerOrder(Order order) {
        if (OrderType.BUY.equals(order.getOrderType())) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    @Override
    public List<Order> getBuyOrders() {
        return buyOrders;
    }

    @Override
    public List<Order> getSellOrders() {
        return sellOrders;
    }
}
