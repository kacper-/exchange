package com.km.trader;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.km.model.Order;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BookBuilder {

    public static Map<String, List<Order>> fromStream(InputStream in) throws IOException {
        List<Order> list = new ArrayList<>();
        Iterator<Order> iterator = read(in);
        iterator.forEachRemaining(list::add);

        return list.stream().collect(Collectors.groupingBy(Order::getType));
    }

    private static Iterator<Order> read(InputStream in) throws IOException {
        return new CsvMapper()
                .readerWithTypedSchemaFor(Order.class)
                .with(CsvParser.Feature.SKIP_EMPTY_LINES)
                .readValues(new InputStreamReader(in));
    }
}
