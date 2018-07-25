package com.ignaciosuay.fruitshopapi.fixture;

import com.ignaciosuay.fruitshopapi.model.Item;

import java.util.List;

import static java.util.Arrays.asList;

public class ItemsFixture {

    public static List<Item> anItemList() {
        return asList(new Item("apple", 60),
            new Item("orange", 25));
    }
}
