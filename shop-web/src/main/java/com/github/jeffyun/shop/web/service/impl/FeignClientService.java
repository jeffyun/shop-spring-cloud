package com.github.jeffyun.shop.web.service.impl;

import com.github.jeffyun.shop.order.service.api.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jeff.wu
 * @date 2019/6/24 15:08
 */
@FeignClient(name = "shop-order-server")
public interface FeignClientService extends OrderService {

}
