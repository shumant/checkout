package com.shuman;

import com.shuman.promotion.BasketSumPromotion;
import com.shuman.promotion.ItemCountPromotion;
import com.shuman.promotion.Promotion;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {
    private final static Map<Long, Product> PRODUCTS = Map.of(
        1L, new Product(1L,  9.25),
        2L, new Product(2L, 45.00),
        3L, new Product(3L, 19.95)
    );

    private final static List<Promotion> PROMOTIONS = List.of(
        new ItemCountPromotion(1L, 2, 8.5),
        new BasketSumPromotion(60, 0.1)
    );

    @Test
    void test_1() {
        // given
        var checkout = new Checkout(PROMOTIONS);
        checkout.scan(PRODUCTS.get(1L));
        checkout.scan(PRODUCTS.get(2L));
        checkout.scan(PRODUCTS.get(3L));

        // when
        var total = checkout.total();

        // then
        // should be 66.79, NOT 66.78 because discounts are applied and rounded on each product level
        assertEquals(66.79, total);
    }

    @Test
    void test_2() {
        // given
        var checkout = new Checkout(PROMOTIONS);
        checkout.scan(PRODUCTS.get(1L));
        checkout.scan(PRODUCTS.get(3L));
        checkout.scan(PRODUCTS.get(1L));

        // when
        var total = checkout.total();

        // then
        assertEquals(36.95, total);
    }

    @Test
    void test_3() {
        // given
        var checkout = new Checkout(PROMOTIONS);
        checkout.scan(PRODUCTS.get(1L));
        checkout.scan(PRODUCTS.get(2L));
        checkout.scan(PRODUCTS.get(1L));
        checkout.scan(PRODUCTS.get(3L));

        // when
        var total = checkout.total();

        // then
        assertEquals(73.76, total);
    }
}
