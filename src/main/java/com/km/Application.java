package com.km;

import com.km.trader.BookBuilder;
import com.km.trader.Trader;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            Trader trader = new Trader(BookBuilder.buildFromSTDIN());
            System.out.print(trader.trade());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}