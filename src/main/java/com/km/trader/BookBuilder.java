package com.km.trader;

import com.km.model.Order;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookBuilder {
    public static Book build(Iterator<Order> iterator) {
        Spliterator<Order> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);

        Map<String, List<Order>> allOrders =
                StreamSupport.stream(spliterator, false)
                        .collect(Collectors.groupingBy(Order::getType));

        List<Order> buy = allOrders.get(Book.BUY);
        List<Order> sell = allOrders.get(Book.SELL);

        buy.sort(Comparator.comparingLong(Order::getPrice).reversed());
        sell.sort(Comparator.comparingLong(Order::getPrice));

        return new Book(buy, sell);
    }
}
