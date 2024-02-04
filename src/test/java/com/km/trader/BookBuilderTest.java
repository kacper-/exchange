package com.km.trader;

import com.km.model.Order;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BookBuilderTest {

    @Test
    public void testEmptyFile() throws IOException {
        Map<String, List<Order>> map = BookBuilder.fromStream(fileToStream("emptyFile.txt"));
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testOnlyColumnErrors() throws IOException {
        Map<String, List<Order>> map = BookBuilder.fromStream(fileToStream("onlyColumnErrors.txt"));
        Assert.assertTrue(map.isEmpty());
    }

    private InputStream fileToStream(String name) throws IOException {
        return getClass().getClassLoader().getResourceAsStream(name);
    }
}