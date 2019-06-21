package com.github.jeffyun.shop.web.controller;

import com.github.jeffyun.shop.common.model.ResponseResult;
import com.github.jeffyun.shop.web.model.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jeff.uw
 * @date 2019/6/21 13:56
 */
@RestController
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "main")
    public ResponseResult orderConfirm(OrderVO orderVO) {
        logger.info(">>>>>> order confirm begin");
//        restTemplate.getForObject("http://shop-order-server",String.class);
        logger.info(">>>>>> order confirm end");
        return ResponseResult.ok();

    }
}
