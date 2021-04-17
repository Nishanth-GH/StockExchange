package com.navi.pojo;

import lombok.Data;

@Data
public class OrderTransaction {
    private long sourceId;
    private long destinationId;
    private double matchingPrice;
    private int quantity;
}
