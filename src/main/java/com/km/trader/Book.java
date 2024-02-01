package com.km.trader;

import com.km.model.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Book {
    public static final String BUY = "B";
    public static final String SELL = "S";
    private static final String BLANK = "                  ";
    private static final String SEPARATOR = " | ";
    private static final String END_LINE = "\n";
    private static final String BUY_FORMAT = "% ,11d % 6d";
    private static final String SELL_FORMAT = "% 6d % ,11d";

    final LinkedList<Order> buy;
    final LinkedList<Order> sell;

    public Book(List<Order> buy, List<Order> sell) {
        this.buy = new LinkedList<>(buy);
        this.sell = new LinkedList<>(sell);
    }

    @Override
    public String toString() {
        int bigger = Math.max(buy.size(), sell.size());
        int smaller = Math.min(buy.size(), sell.size());
        boolean buyIsLonger = bigger == buy.size();
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < smaller; i++) {
            b.append(buyToString(buy.get(i)));
            b.append(SEPARATOR);
            b.append(sellToString(sell.get(i)));
            b.append(END_LINE);
        }

        for (int i = smaller; i < bigger; i++) {
            b.append(buyToString(buyIsLonger ? buy.get(i) : null));
            b.append(SEPARATOR);
            b.append(sellToString(buyIsLonger ? null : sell.get(i)));
            b.append(END_LINE);
        }

        return b.toString();
    }

    private String buyToString(Order o) {
        if (o == null)
            return BLANK;
        return String.format(Locale.ENGLISH, BUY_FORMAT, o.getQuantity(), o.getPrice());
    }

    private String sellToString(Order o) {
        if (o == null)
            return BLANK;
        return String.format(Locale.ENGLISH, SELL_FORMAT, o.getPrice(), o.getQuantity());
    }
}
