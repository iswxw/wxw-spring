package com.wxw.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: wxw
 * @create: 2020-03-07-17:13
 */
@Component
@ConfigurationProperties(prefix = "order")
@Data
public class Order {

    private String OrderId;
    private String OrderName;
}
