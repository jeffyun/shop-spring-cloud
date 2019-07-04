package com.github.jeffyun.shop.order.service.api;

import com.github.jeffyun.shop.common.model.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author jeff.wu
 * @date 2019/6/26 10:05
 */
@Component
public class FeignClientFallBack implements OrderService {
    /**
     * 创建订单
     *
     * @return
     */
    @Override
    public ResponseResult createOrder() {
        return ResponseResult.fail().setMsg("降级处理");
    }
}
