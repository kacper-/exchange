package com.km;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.km.model.Book;
import com.km.model.Order;
import com.km.model.TradeLog;
import com.km.trader.BookBuilder;
import com.km.trader.Trader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Application {
    public static void main(String[] args) {
        try {
            Iterator<Order> iterator = new CsvMapper()
                    .readerWithTypedSchemaFor(Order.class)
                    .readValues(new InputStreamReader(System.in));

            Book book = BookBuilder.build(iterator);
            TradeLog tradeLog = Trader.trade(book);

            System.out.print(tradeLog);
            System.out.print(book);

        } catch (IOException e) {
            System.exit(-1);
        }
    }
}