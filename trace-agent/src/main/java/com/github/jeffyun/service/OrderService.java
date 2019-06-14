package com.github.jeffyun.service;

import com.github.jeffyun.bean.OrderDO;

import java.util.List;

/**
 * @author jianfeng.Wu
 * @date 2019/6/14 17:37
 */
public interface OrderService {

    /**
     * 查询所有订单信息
     * @param
     * @return java.util.List<com.github.jeffyun.bean.OrderDO>
     * @author jianfeng.wu
     * @date 2019/6/14 17:44
     */
    List<OrderDO> listOrder();

    /**
     * 根据订单ID查询订单信息
     * @param orderId 订单ID
     * @return com.github.jeffyun.bean.OrderDO
     * @author jianfeng.wu
     * @date 2019/6/14 17:44
     */
    OrderDO getOrderByOrderId(Long orderId);
}
