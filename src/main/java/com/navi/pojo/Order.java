package com.navi.pojo;

import lombok.Data;

@Data
public class Order {
    private long id;
    private String timestamp;
    private String stockName;
    private OrderType orderType;
    private double amount;
    private int quantity;
}
