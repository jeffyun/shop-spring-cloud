package com.github.jeffyun.shop.order.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author jeff.wu
 * @date 2019/6/24 14:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderControllerTest {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void createOrder() {
        webTestClient.get().uri("/order/create")
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult().toString()
        ;


    }
}