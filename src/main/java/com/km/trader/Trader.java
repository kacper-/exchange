package com.km.trader;

import com.km.loggers.BookLogger;
import com.km.loggers.TradeLogger;
import com.km.model.Order;
import com.km.model.Trade;

import java.io.IOException;
import java.util.*;

/**
 * Main logic class.
 * Process book and creates transaction if possible
 * Returns result in form of formatted string representation of transactions and book if not fully processed
 */
public class Trader {
    private static final String BUY = "B";
    private static final String SELL = "S";
    private final List<Trade> log = new ArrayList<>();
    private final LinkedList<Order> buy;
    private final LinkedList<Order> sell;

    /**
     * Creates instance based on BUY and SELL lists
     * Applies sorting for both lists
     *
     * @param book map containing BUY and SELL lists
     */
    public Trader(Map<String, List<Order>> book) {
        book.putIfAbsent(BUY, Collections.emptyList());
        book.putIfAbsent(SELL, Collections.emptyList());
        book.get(BUY).sort(Comparator.comparingLong(Order::getPrice).reversed());
        book.get(SELL).sort(Comparator.comparingLong(Order::getPrice));

        this.buy = new LinkedList<>(book.get(BUY));
        this.sell = new LinkedList<>(book.get(SELL));
    }

    /**
     * Processes book and returns formatted transaction and book logs
     *
     * @return formatted output representing transactions and book
     * @throws IOException
     */
    public String trade() throws IOException {
        while (isTradeAvailable())
            settle();

        return TradeLogger.log(log).append(BookLogger.log(buy, sell)).toString();
    }

    private void settle() {
        Order b = buy.remove();
        Order s = sell.remove();

        long min = Math.min(b.getQuantity(), s.getQuantity());
        log.add(new Trade(b.getId(), s.getId(), s.getPrice(), min));

        if (min < b.getQuantity())
            buy.push(new Order(b.getId(), b.getType(), b.getPrice(), b.getQuantity() - min));
        if (min < s.getQuantity())
            sell.push(new Order(s.getId(), s.getType(), s.getPrice(), s.getQuantity() - min));
    }

    private boolean isTradeAvailable() {
        if (buy.isEmpty() || sell.isEmpty())
            return false;

        return buy.peek().getPrice() >= sell.peek().getPrice();
    }
}
