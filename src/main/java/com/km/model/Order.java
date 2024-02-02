package com.km.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Immutable Order POJO class
 * Used also as model/schema for jackson-databind CSV file loader
 */
@JsonPropertyOrder({"id", "type", "price", "quantity"})
public class Order {
    private final String id;
    private final String type;
    private final long price;
    private final long quantity;

    /**
     * Workaround for jackson-databind iterator deserialization
     */
    public Order() {
        id = null;
        type = null;
        price = 0;
        quantity = 0;
    }

    public Order(String id, String type, long price, long quantity) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public long getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }
}
