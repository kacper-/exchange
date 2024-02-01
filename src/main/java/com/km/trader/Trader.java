package com.km.trader;

import com.km.model.Order;
import com.km.model.Trade;

public class Trader {
    public static TradeLog trade(Book book) {
        TradeLog tradeLog = new TradeLog();

        while (isTradeAvailable(book))
            settle(book, tradeLog);

        return tradeLog;
    }

    private static void settle(Book book, TradeLog tradeLog) {
        Order buy = book.buy.remove();
        Order sell = book.sell.remove();

        long min = Math.min(buy.getQuantity(),sell.getQuantity());
        tradeLog.add(new Trade(buy.getId(), sell.getId(), sell.getPrice(), min));

        if (min < buy.getQuantity())
            book.buy.push(new Order(buy.getId(), buy.getType(), buy.getPrice(), buy.getQuantity() - min));
        if (min < sell.getQuantity())
            book.sell.push(new Order(sell.getId(), sell.getType(), sell.getPrice(), sell.getQuantity() - min));
    }

    private static boolean isTradeAvailable(Book book) {
        if (book.buy.isEmpty() || book.sell.isEmpty())
            return false;

        return book.buy.peek().getPrice() >= book.sell.peek().getPrice();
    }
}
