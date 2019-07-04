package com.github.jeffyun.shop.order.service.api;

import com.github.jeffyun.shop.common.model.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author jeff.wu
 * @date 2019/6/24 17:19
 */
@FeignClient(name = "SHOP-ORDER-SERVER",fallback = FeignClientFallBack.class,path = "/order")
public interface OrderService {

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/create")
    ResponseResult createOrder();
}
