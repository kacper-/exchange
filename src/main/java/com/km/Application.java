package com.km;

import com.km.trader.BookBuilder;
import com.km.trader.Trader;

import java.io.IOException;

/**
 * Application entry point
 * Reads from STDIN and provides result to STDOUT
 */
public class Application {
    public static void main(String[] args) {
        try {
            Trader trader = new Trader(BookBuilder.fromStream(System.in));
            System.out.print(trader.trade());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}