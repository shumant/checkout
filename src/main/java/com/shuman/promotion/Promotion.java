package com.shuman.promotion;

import com.shuman.BasketEntry;

import java.util.Map;
import java.util.function.Consumer;

public interface Promotion extends Consumer<Map<Long, BasketEntry>> {

}
