package com.km.trader;

import com.km.model.Trade;

import java.util.ArrayList;
import java.util.List;

public class TradeLog {
    private static final String END_LINE = "\n";
    private static final String PREFIX = "trade ";
    private static final String SEPARATOR = ",";
    private final List<Trade> log = new ArrayList<>();

    public void add(Trade trade) {
        log.add(trade);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        log.forEach(t -> addLine(b, t));
        return b.toString();
    }

    private void addLine(StringBuilder b, Trade trade) {
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
