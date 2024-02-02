package com.km.loggers;

import com.km.model.Trade;

import java.util.List;

public class TradeLogger {
    private static final String END_LINE = "\n";
    private static final String PREFIX = "trade ";
    private static final String SEPARATOR = ",";

    public static StringBuilder log(List<Trade> trades) {
        StringBuilder b = new StringBuilder();
        trades.forEach(t -> addLine(b, t));
        return b;
    }

    private static void addLine(StringBuilder b, Trade trade) {
        b.append(PREFIX);
        b.append(trade.getBuyId());
        b.append(SEPARATOR);
        b.append(trade.getSellId());
        b.append(SEPARATOR);
        b.append(trade.getPrice());
        b.append(SEPARATOR);
        b.append(trade.getQuantity());
        b.append(END_LINE);
    }
}
