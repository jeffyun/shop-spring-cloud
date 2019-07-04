package com.github.jeffyun.shop.web.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author jeff.wu
 * @date 2019/6/24 15:07
 */
@EnableFeignClients(basePackages = "com.github.jeffyun.shop.order.service.api")
@Configuration
public class FeignConfig {

}
