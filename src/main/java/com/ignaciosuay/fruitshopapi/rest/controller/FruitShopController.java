package com.ignaciosuay.fruitshopapi.rest.controller;

import com.ignaciosuay.fruitshopapi.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fruits")
@Log4j2
@RequiredArgsConstructor
public class FruitShopController {

    private final CheckoutService checkoutService;

    @PostMapping("checkout")
    public BigDecimal checkout(@RequestBody List<String> fruits) {
        log.info("Checking out: ", fruits);
        return checkoutService.checkout(fruits);
    }
}
