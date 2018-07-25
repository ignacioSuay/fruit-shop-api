package com.ignaciosuay.fruitshopapi.rest.controller;

import com.ignaciosuay.fruitshopapi.FruitShopApiApplication;
import com.ignaciosuay.fruitshopapi.rest.model.ErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = FruitShopApiApplication.class)
public class FruitShopControllerTest {

    @LocalServerPort
    private Integer randomServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldGet200AndCheckoutResult() throws Exception {

        //Given
        List<String> fruits = asList("apple", "orange");
        String url = String.format("http://localhost:%s/fruits/checkout", randomServerPort);

        //When
        ResponseEntity<BigDecimal> checkoutAmount = testRestTemplate.postForEntity(url, fruits, BigDecimal.class);

        //Then
        assertThat(checkoutAmount.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(checkoutAmount.getBody().floatValue()).isEqualTo(0.85f);
    }

    @Test
    public void shouldGet200AndDiscountedResult() throws Exception {

        //Given
        List<String> fruits = asList("apple", "apple", "orange", "orange", "orange");
        String url = String.format("http://localhost:%s/fruits/checkout", randomServerPort);

        //When
        ResponseEntity<BigDecimal> checkoutAmount = testRestTemplate.postForEntity(url, fruits, BigDecimal.class);

        //Then
        assertThat(checkoutAmount.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(checkoutAmount.getBody().floatValue()).isEqualTo(1.10f);
    }

    @Test
    public void shouldGet400WithWrongInputFruits() throws Exception {

        //Given
        List<String> fruits = asList("apple", "asdaqasd");
        String url = String.format("http://localhost:%s/fruits/checkout", randomServerPort);

        //When
        ResponseEntity<ErrorResponse> errorResponse = testRestTemplate.postForEntity(url, fruits, ErrorResponse.class);

        //Then
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getBody().getMessage()).isEqualTo("Cannot find fruit: asdaqasd");
        assertThat(errorResponse.getBody().getError()).isEqualTo("Bad Request");
        assertThat(errorResponse.getBody().getPath()).isEqualTo("/fruits/checkout");
    }

}