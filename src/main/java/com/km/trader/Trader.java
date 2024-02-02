package com.km.trader;

import com.km.loggers.BookLogger;
import com.km.loggers.TradeLogger;
import com.km.model.Order;
import com.km.model.Trade;

import java.io.IOException;
import java.util.*;

public class Trader {
    private final List<Trade> log = new ArrayList<>();
    private final LinkedList<Order> buy;
    private final LinkedList<Order> sell;

    public Trader(Map<String, List<Order>> book) {
        this.buy = new LinkedList<>(book.getOrDefault(BookBuilder.BUY, Collections.emptyList()));
        this.sell = new LinkedList<>(book.getOrDefault(BookBuilder.SELL, Collections.emptyList()));
    }

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
