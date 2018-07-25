package com.ignaciosuay.fruitshopapi.service;

import com.ignaciosuay.fruitshopapi.configuration.ItemsConfiguration;
import com.ignaciosuay.fruitshopapi.fixture.ItemsFixture;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {

    private ItemsConfiguration itemsConfiguration = mock(ItemsConfiguration.class);
    private CheckoutService checkoutService = new CheckoutService(itemsConfiguration);

    @Before
    public void setup() {
        when(itemsConfiguration.getItems()).thenReturn(ItemsFixture.anItemList());
    }

    @Test
    public void shouldBe0IfThereAreNoFruits() throws Exception {

        //Given
        List<String> fruits = new ArrayList<>();

        //When
        BigDecimal result = checkoutService.checkout(fruits);

        //Then
        assertThat(result.intValue()).isEqualTo(0);
    }

    @Test
    public void shouldCalculateSumOfFruits() throws Exception {

        //Given
        List<String> fruits = asList("apple", "orange", "orange");

        //When
        BigDecimal result = checkoutService.checkout(fruits);

        //Then
        assertThat(result.floatValue()).isEqualTo(1.10f);
    }

}