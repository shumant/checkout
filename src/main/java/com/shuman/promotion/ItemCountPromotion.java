package com.shuman.promotion;

import com.shuman.BasketEntry;

import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Verify.verify;

public class ItemCountPromotion implements Promotion {

    private final long targetProductId;
    private final int requiredCount;
    private final double newPrice;

    public ItemCountPromotion(long targetProductId, int requiredCount, double newPrice) {
        verify(newPrice >= 0, "Price should be > 0");
        this.targetProductId = targetProductId;
        this.requiredCount = requiredCount;
        this.newPrice = newPrice;
    }

    @Override
    public void accept(Map<Long, BasketEntry> basket) {
        if (Optional.ofNullable(basket.get(targetProductId)).map(basketEntry -> basketEntry.count).orElse(0) >= requiredCount) {
            basket.computeIfPresent(targetProductId,
                (productId, oldBasketEntry) -> oldBasketEntry.withPrice(newPrice));
        }
    }
}
