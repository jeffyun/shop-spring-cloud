package com.github.jeffyun.shop.web.controller;

import com.github.jeffyun.shop.common.model.ResponseResult;
import com.github.jeffyun.shop.web.model.vo.OrderVO;
import com.github.jeffyun.shop.web.service.impl.FeignClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author jeff.wu
 * @date 2019/6/21 13:56
 */
@RestController
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Resource
    private FeignClientService feignClientService;


    @RequestMapping(value = "main")
    public ResponseResult orderConfirm(OrderVO orderVO) {
        logger.info(">>>>>> order confirm begin");
//        ResponseResult result = restTemplate.getForObject("http://SHOP-ORDER-SERVER/order/create", ResponseResult.class);

        ResponseResult result = feignClientService.createOrder();
        logger.info(">>>>>> main controller result = {}", result);
        logger.info(">>>>>> order confirm end");
        return ResponseResult.ok();

    }
}
