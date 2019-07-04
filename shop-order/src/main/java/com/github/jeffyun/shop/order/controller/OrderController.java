package com.github.jeffyun.shop.order.controller;

import com.github.jeffyun.shop.common.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jianfeng.Wu
 * @date 2019/6/21 11:37
 */
@RestController
public class OrderController {

    public final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/order/create")
    public ResponseResult createOrder(String orderId) {
        logger.info(">>>>>>  create order");
        if (true){
            throw new RuntimeException("error");
        }

        ResponseResult result = restTemplate.getForObject("http://localhost:8091//product/update/stock", ResponseResult.class);
        logger.info(">>>>>> order controller result = {}", result);
        return ResponseResult.ok().setMsg("create order success");
    }


}
