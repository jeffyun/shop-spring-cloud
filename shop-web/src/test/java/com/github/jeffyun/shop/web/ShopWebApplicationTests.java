package com.github.jeffyun.shop.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Consumer;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ShopWebApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("1212");
        Consumer consumer = o -> System.out.println(o);
        consumer.accept("sf");
    }

}
