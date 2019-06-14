package com.github.jeffyun.trace;

import com.github.jeffyun.service.OrderService;
import com.github.jeffyun.service.impl.OrderServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jianfeng.Wu
 * @date 2019/6/13 11:20
 */
public class TranceAgentTest {

    public static void main(String[] args) {
        System.out.println("test main begin");

        OrderService orderService = new OrderServiceImpl();
        orderService.getOrderByOrderId(1l);
    }

    @Test
    public void test(){
        System.out.println((1&5));
    }
}