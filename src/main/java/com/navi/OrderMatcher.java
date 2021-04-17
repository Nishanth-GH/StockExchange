package com.navi;

import com.navi.pojo.Order;

/**
 *  Exposes methods to check matching orders while an order is placed
 */
public interface OrderMatcher {
    void checkForMatchingSellOrder(Order order);

    void checkForMatchingBuyOrder(Order order);
}
