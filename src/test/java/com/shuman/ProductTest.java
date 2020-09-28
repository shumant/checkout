package com.shuman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    @Test
    public void should_apply_discount() {
        // given
        final var product = new Product(2L, 19.95);

        // when
        final var price = product.withDiscount(0.1).price;

        // then
        assertEquals(17.96, price.doubleValue());
    }

    @Test
    public void should_set_price() {
        // given
        final var product = new Product(2L, 19.95);

        // when
        final var price = product.withPrice(23.0).price;

        // then
        assertEquals(23.0, price.doubleValue());
    }
}