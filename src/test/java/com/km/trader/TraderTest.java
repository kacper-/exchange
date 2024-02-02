package com.km.trader;

import com.km.model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class TraderTest {

    @Test
    public void testEmptyBook() {
        Trader trader = new Trader(Collections.emptyMap());
        try {
            String s = trader.trade();
            Assert.assertEquals("", s);
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Test
    public void testBookOnly() {
        Map<String, List<Order>> book = new HashMap<>();
        List<Order> buy = new ArrayList<>();
        List<Order> sell = new ArrayList<>();

        buy.add(new Order("10000","B",98,25500));
        buy.add(new Order("10003","B",99,50000));

        sell.add(new Order("10005","S",105,20000));
        sell.add(new Order("10001","S",100,500));
        sell.add(new Order("10002","S",100,10000));
        sell.add(new Order("10004","S",103,100));

        book.put(BookBuilder.BUY, buy);
        book.put(BookBuilder.SELL, sell);

        Trader trader = new Trader(book);
        try {
            String s = trader.trade();

            Assert.assertEquals("8ff13aad3e61429bfb5ce0857e846567", md5(s));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    private String md5(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(s.getBytes());
        byte[] bytes = md.digest();
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}