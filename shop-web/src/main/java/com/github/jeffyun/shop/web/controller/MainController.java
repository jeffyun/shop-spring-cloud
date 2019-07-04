package com.github.jeffyun.shop.web.controller;

import com.github.jeffyun.shop.common.model.ResponseResult;
import com.github.jeffyun.shop.order.service.api.OrderService;
import com.github.jeffyun.shop.web.model.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jeff.wu
 * @date 2019/6/21 13:56
 */
@RestController
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Resource
    private OrderService orderService;


    @RequestMapping(value = "main")
    public ResponseResult orderConfirm(OrderVO orderVO) {
        logger.info(">>>>>> order confirm begin");
        ResponseResult result = orderService.createOrder();
        logger.info(">>>>>> main controller result = {}", result);
        logger.info(">>>>>> order confirm end");
        return result;

    }
}
