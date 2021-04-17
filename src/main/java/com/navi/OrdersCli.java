package com.navi;

import com.navi.pojo.Order;
import com.navi.pojo.OrderType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Reads input from a file, formats and calls respective services
 */
public class OrdersCli {

    public static void main(String[] args) {
        OrderTransactionLedger ledger = new OrderTransactionLedger();
        OrderBookImpl orderBookImpl = new OrderBookImpl();
        OrderMatcherRealTime orderMatcher = new OrderMatcherRealTime(orderBookImpl, ledger);
        OrderManger orderManger = new OrderManger(orderBookImpl, orderMatcher);
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                orderManger.createOrder(transformStringToOrder(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ledger.printTransactionsInLedger();
    }

    public static Order transformStringToOrder(String orderString) {
        Order order = new Order();
        String[] entries = orderString.split("\\s+");
        order.setId(Long.valueOf(entries[0].substring(1).trim()));
        order.setTimestamp(entries[1].trim());
        order.setStockName(entries[2].trim());
        order.setOrderType((entries[3].trim().equals("sell")) ? OrderType.SELL : OrderType.BUY);
        order.setAmount(Double.valueOf(entries[4].trim()));
        order.setQuantity(Integer.parseInt(entries[5].trim()));
        System.out.println("transformed order is " + order);
        return order;
    }
}
