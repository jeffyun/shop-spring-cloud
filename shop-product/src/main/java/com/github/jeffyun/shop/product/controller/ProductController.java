package com.github.jeffyun.shop.product.controller;

import com.github.jeffyun.shop.common.model.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jeff.wu
 * @date 2019/6/21 13:40
 */
@RestController(value = "/product")
public class ProductController {

    public final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/update/stock")
    public ResponseResult updateStock(Long productId) {
        logger.info(">>>>>>  update stock with {}", productId);
        return ResponseResult.ok();
    }
}
