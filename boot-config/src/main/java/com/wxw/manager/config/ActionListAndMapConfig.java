package com.wxw.manager.config;

import com.wxw.manager.config.CompositePropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * @author: wxw
 * @date: 2021-04-06-22:04
 * @link:
 * @description:
 */
@Component
@PropertySource(value = {"classpath:config/actions.yml","file:./config/actions.yml"},
        ignoreResourceNotFound = true,factory = CompositePropertySourceFactory.class
)
@ConfigurationProperties(prefix = "actions")
@Data
public class ActionListAndMapConfig {

    private List<Map<String,String>> actionList;
}
