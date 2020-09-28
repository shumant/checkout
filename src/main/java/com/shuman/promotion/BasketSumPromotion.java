package com.shuman.promotion;

import com.shuman.BasketEntry;

import java.util.Map;

import static com.google.common.base.Verify.verify;

public class BasketSumPromotion implements Promotion {

    private final double requiredSum;
    private final double discount;

    public BasketSumPromotion(double requiredSum, double discount) {
        verify(discount > 0 && discount <= 1, "Discount should be 0 < discount <= 1");
        this.requiredSum = requiredSum;
        this.discount = discount;
    }

    @Override
    public void accept(Map<Long, BasketEntry> basket) {
        final var totalSum = basket.values().stream()
            .mapToDouble(BasketEntry::total)
            .sum();

        if (totalSum >= requiredSum) {
            basket.replaceAll(
                (productId, basketEntry) -> basketEntry.withDiscount(discount)
            );
        }
    }
}
