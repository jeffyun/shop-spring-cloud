package com.github.jeffyun.bean;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author jianfeng.Wu
 * @date 2019/6/14 17:39
 */
public class OrderDO implements Serializable {

    Long orderId;
    String orderName;

    public OrderDO() {
    }

    public OrderDO(Long orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderDO setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderName() {
        return orderName;
    }

    public OrderDO setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderDO.class.getSimpleName() + "[", "]")
                .add("orderId=" + orderId)
                .add("orderName='" + orderName + "'")
                .toString();
    }
}
