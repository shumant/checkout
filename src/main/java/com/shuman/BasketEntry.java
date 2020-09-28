package com.shuman;

public class BasketEntry {

    public final Product product;
    public final int count;

    public BasketEntry(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public BasketEntry withDiscount(double discount) {
        return new BasketEntry(product.withDiscount(discount), count);
    }

    public BasketEntry withPrice(double price) {
        return new BasketEntry(product.withPrice(price), count);
    }

    public BasketEntry withIncrementedCount() {
        return new BasketEntry(product, count + 1);
    }

    public double total() {
        return product.price.doubleValue() * count;
    }
}
