package com.ignaciosuay.fruitshopapi.service;

import com.ignaciosuay.fruitshopapi.configuration.ItemsConfiguration;
import com.ignaciosuay.fruitshopapi.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final ItemsConfiguration itemsConfiguration;

    public BigDecimal checkout(List<String> fruits) {

        int totalInPence = fruits.stream()
            .map(this::mapFruitToItem)
            .mapToInt(Item::getPrice)
            .sum();

        return penceToPounds(totalInPence);
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
