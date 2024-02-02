package com.km.trader;

import com.km.model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraderTest {

    @Test
    public void testEmptyBook() throws IOException {
        Trader trader = new Trader(new HashMap<>());
        String s = trader.trade();

        Assert.assertEquals("", s);
    }

    @Test
    public void testEmptyFile() throws IOException {
        Trader trader = new Trader(loadFromFile("t00.txt"));
        String s = trader.trade();

        Assert.assertEquals("", s);
    }

    @Test
    public void testBookWithMD5() throws IOException, NoSuchAlgorithmException {
        Trader trader = new Trader(loadFromFile("t01.txt"));
        String s = trader.trade();

        Assert.assertEquals("8ff13aad3e61429bfb5ce0857e846567", md5(s));
    }

    @Test
    public void testTradeAndBookWithMD5() throws IOException, NoSuchAlgorithmException {
        Trader trader = new Trader(loadFromFile("t02.txt"));
        String s = trader.trade();

        Assert.assertEquals("ce8e7e5ab26ab5a7db6b7d30759cf02e", md5(s));
    }

    @Test
    public void testOnlySell() throws IOException {
        Trader trader = new Trader(loadFromFile("t03.txt"));
        String s = trader.trade();

        Assert.assertEquals("                   |    105      20,000\n", s);
    }

    @Test
    public void testOnlyBuy() throws IOException {
        Trader trader = new Trader(loadFromFile("t04.txt"));
        String s = trader.trade();

        Assert.assertEquals("     25,500     98 |                   \n", s);
    }

    @Test
    public void testBrokenFileWithMD5() throws IOException, NoSuchAlgorithmException {
        Trader trader = new Trader(loadFromFile("t05.txt"));
        String s = trader.trade();

        Assert.assertEquals("ce8e7e5ab26ab5a7db6b7d30759cf02e", md5(s));
    }

    @Test
    public void testAllSettled() throws IOException {
        Trader trader = new Trader(loadFromFile("t06.txt"));
        String s = trader.trade();

        Assert.assertEquals("trade 10000,10001,99,900\n", s);
    }

    private String md5(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        byte[] bytes = md.digest();
        StringBuilder result = new StringBuilder();

        for (byte b : bytes)
            result.append(String.format("%02x", b));

        return result.toString();
    }

    private Map<String, List<Order>> loadFromFile(String name) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(name);
        return BookBuilder.fromStream(in);
    }

}