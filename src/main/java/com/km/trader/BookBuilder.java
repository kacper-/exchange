package com.km.trader;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.km.model.Order;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BookBuilder {
    public static final String BUY = "B";
    public static final String SELL = "S";

    public static Map<String, List<Order>> buildFromSTDIN() throws IOException {
        List<Order> list = new ArrayList<>();
        Iterator<Order> iterator = readFromIn();
        iterator.forEachRemaining(list::add);

        Map<String, List<Order>> book = list.stream().collect(Collectors.groupingBy(Order::getType));

        book.putIfAbsent(BUY, Collections.emptyList());
        book.putIfAbsent(SELL, Collections.emptyList());
        book.get(BUY).sort(Comparator.comparingLong(Order::getPrice).reversed());
        book.get(SELL).sort(Comparator.comparingLong(Order::getPrice));

        return book;
    }

    private static Iterator<Order> readFromIn() throws IOException {
        return new CsvMapper()
                .readerWithTypedSchemaFor(Order.class)
                .with(CsvParser.Feature.SKIP_EMPTY_LINES)
                .readValues(new InputStreamReader(System.in));
    }
}
