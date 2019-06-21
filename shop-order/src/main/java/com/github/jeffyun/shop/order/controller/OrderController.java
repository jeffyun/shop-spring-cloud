package com.github.jeffyun.shop.order.controller;

import com.github.jeffyun.shop.common.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jianfeng.Wu
 * @date 2019/6/21 11:37
 */
@RestController(value = "/order")
public class OrderController {

    public final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/create")
    public ResponseResult createOrder() {
        logger.info(">>>>>>  create order");
        return ResponseResult.ok();
    }


}
