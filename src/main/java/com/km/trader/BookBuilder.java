package com.km.trader;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.km.model.Order;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Loads CVS file/stream into book.
 * Book is a Map that contains two lists, BUY and SELL
 */
public class BookBuilder {

    public static Map<String, List<Order>> fromStream(InputStream in) throws IOException {
        List<Order> list = new ArrayList<>();
        Iterator<Order> iterator = read(in);
        iterator.forEachRemaining(list::add);

        List<Order> nonNullList = filterNulls(list);

        return nonNullList.stream().collect(Collectors.groupingBy(Order::getType));
    }

    private static List<Order> filterNulls(List<Order> list) {
        return list.stream()
                .filter(o -> !o.getId().isEmpty())
                .filter(o -> o.getPrice() > 0)
                .filter(o -> o.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    private static Iterator<Order> read(InputStream in) throws IOException {
        return new CsvMapper()
                .readerWithTypedSchemaFor(Order.class)
                .with(CsvParser.Feature.SKIP_EMPTY_LINES)
                .readValues(new InputStreamReader(in));
    }
}
