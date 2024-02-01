package com.km.model;

public class Trade {
    private final String buyId;
    private final String sellId;
    private final long price;
    private final long quantity;

    public Trade(String buyId, String sellId, long price, long quantity) {
        this.buyId = buyId;
        this.sellId = sellId;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBuyId() {
        return buyId;
    }

    public String getSellId() {
        return sellId;
    }

    public long getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

}
