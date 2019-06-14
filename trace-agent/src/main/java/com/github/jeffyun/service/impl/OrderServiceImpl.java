package com.github.jeffyun.service.impl;

import com.github.jeffyun.bean.OrderDO;
import com.github.jeffyun.service.OrderService;

import java.util.List;

/**
 * @author jianfeng.Wu
 * @date 2019/6/14 17:45
 */
public class OrderServiceImpl implements OrderService {
    /**
     * 查询所有订单信息
     *
     * @return java.util.List<com.github.jeffyun.bean.OrderDO>
     * @author jianfeng.wu
     * @date 2019/6/14 17:44
     */
    @Override
    public List<OrderDO> listOrder() {
        System.out.println("list order");
        return null;
    }

    /**
     * 根据订单ID查询订单信息
     *
     * @param orderId 订单ID
     * @return com.github.jeffyun.bean.OrderDO
     * @author jianfeng.wu
     * @date 2019/6/14 17:44
     */
    @Override
    public OrderDO getOrderByOrderId(Long orderId) {
        System.out.println(" get order by orderId");
        return new OrderDO().setOrderId(orderId);
    }
}
