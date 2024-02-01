package com.km.model;

import java.util.LinkedList;
import java.util.List;

public class Book {
    public static final String BUY = "B";
    public static final String SELL = "S";

    private final LinkedList<Order> buy;
    private final LinkedList<Order> sell;

    public Book(List<Order> buy, List<Order> sell) {
        this.buy = new LinkedList<>(buy);
        this.sell = new LinkedList<>(sell);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(int i=0;i<Math.min(buy.size(), sell.size());i++)
            b.append(buy.get(i)+" "+sell.get(i)+"\n");
        return b.toString();
    }
}
