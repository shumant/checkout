package com.shuman;

import com.shuman.promotion.Promotion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.RoundingMode.HALF_UP;

public class Checkout {

    private final List<Promotion> promotions;

    private final Map<Long, BasketEntry> basket = new HashMap<>();

    // caller controls a promotion rules order
    public Checkout(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void scan(Product product) {
        // assume product price never changes
        basket.merge(product.productId,
            new BasketEntry(product, 1),
            (entry1, entry2) -> entry1.withIncrementedCount()
            );
    }

    public Double total() {
        // copying basket so that we don't loose initial prices
        final var basketCopy = new HashMap<>(basket);
        // applying promotions only when calculation total, because we don't need to show discounted price per product
        promotions.forEach(promotion -> promotion.accept(basketCopy));
        final var total = basketCopy.values().stream()
            .mapToDouble(BasketEntry::total)
            .sum();

        return new BigDecimal(total).setScale(2, HALF_UP).doubleValue();
    }

}
