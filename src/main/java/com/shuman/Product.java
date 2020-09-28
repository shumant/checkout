package com.shuman;

import java.math.BigDecimal;

import static com.google.common.base.Verify.verify;
import static java.math.RoundingMode.HALF_UP;

public class Product {

    public final Long productId;
    public final BigDecimal price;

    public Product(Long productId, Double price) {
        this.productId = productId;
        this.price = new BigDecimal(price).setScale(2, HALF_UP);
    }

    public Product(Long productId, BigDecimal price) {
        this.productId = productId;
        this.price = price.setScale(2, HALF_UP);
    }

    public Product withDiscount(Double discount) {
        verify(discount > 0 && discount <= 1, "Discount should be 0 < discount <= 1");
        var newPrice = price.multiply(new BigDecimal(1-discount));
        return new Product(productId, newPrice);
    }

    public Product withPrice(double price) {
        return new Product(productId, price);
    }
}
