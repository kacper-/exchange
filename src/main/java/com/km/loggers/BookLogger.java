package com.km.loggers;

import com.km.model.Order;

import java.util.List;
import java.util.Locale;

/**
 * Creates String representation of a book (BUY and SELL lists)
 * Applies required formatting
 */
public class BookLogger {

    private static final String BLANK = "                  ";
    private static final String SEPARATOR = " | ";
    private static final String END_LINE = "\n";
    private static final String BUY_FORMAT = "% ,11d % 6d";
    private static final String SELL_FORMAT = "% 6d % ,11d";

    /**
     * Returns string representation of BUY and SELL lists
     * <p>
     * Deals with the case in which lists for BUY and SELL are of different lengths
     * and empty space has to be filled with white spaces
     *
     * @param buy  BUY list
     * @param sell SELL list
     * @return formatted book representation
     */
    public static StringBuilder log(List<Order> buy, List<Order> sell) {
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

        return b;
    }

    private static String buyToString(Order o) {
        if (o == null)
            return BLANK;
        return String.format(Locale.ENGLISH, BUY_FORMAT, o.getQuantity(), o.getPrice());
    }

    private static String sellToString(Order o) {
        if (o == null)
            return BLANK;
        return String.format(Locale.ENGLISH, SELL_FORMAT, o.getPrice(), o.getQuantity());
    }
}
