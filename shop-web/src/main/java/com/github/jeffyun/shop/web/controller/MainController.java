package com.github.jeffyun.shop.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping(value = "main")
    public void sayHello(String name){
        System.out.println(">>>>>> controller  hello :" + name);
    }
}
