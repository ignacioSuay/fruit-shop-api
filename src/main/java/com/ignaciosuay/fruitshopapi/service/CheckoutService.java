package com.ignaciosuay.fruitshopapi.service;

import com.ignaciosuay.fruitshopapi.configuration.ItemsConfiguration;
import com.ignaciosuay.fruitshopapi.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private static final String APPLE = "apple";
    private static final String ORANGE = "orange";

    private final ItemsConfiguration itemsConfiguration;

    public BigDecimal checkout(List<String> fruits) {

        int totalInPence = fruits.stream()
            .map(this::mapFruitToItem)
            .mapToInt(Item::getPrice)
            .sum();

        int finalPrice = totalInPence - discounts(fruits);
        return penceToPounds(finalPrice);
    }

    private int discounts(List<String> fruits) {
        return discountByFruitAndRatio(fruits, APPLE, 2) + discountByFruitAndRatio(fruits, ORANGE, 3);
    }

    private int discountByFruitAndRatio(List<String> fruits, String fruit, int ratio) {
        long numberFruits = findNumberFruit(fruits, fruit);
        Item appleItem = mapFruitToItem(fruit);
        return ((int) numberFruits / ratio) * appleItem.getPrice();
    }

    private long findNumberFruit(List<String> fruits, String key) {
        return fruits.stream()
            .filter(Objects::nonNull)
            .filter(fruit -> fruit.equalsIgnoreCase(key))
            .count();
    }

    private Item mapFruitToItem(String fruit) {
        return itemsConfiguration.getItems().stream()
            .filter(item -> item.getName().equalsIgnoreCase(fruit))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Cannot find fruit: " + fruit));
    }

    private BigDecimal penceToPounds(double pence) {
        return new BigDecimal(pence).divide(new BigDecimal(100), 2, RoundingMode.FLOOR);
    }
}
