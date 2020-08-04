package com.wxw.config;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 10:24 2020/8/4
 * @ Description：获取环境变量
 * @ Version:   v_0.0.1
 */
@Component
public class EnvironmentConfig{

    private static Environment env;

    @Resource
    public void setEnv(Environment env) {
        EnvironmentConfig.env = env;
    }

    /**
     * 通过key获取value value是String并且不可以为空
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return env.getProperty(key);
    }

    /**
     * 通过key获取value 需要指定默认值
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        return env.getProperty(key, defaultValue);
    }

    /**
     * 通过key获取value 并且Value是任意类型
     * @param key
     * @param targetType
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getProperty(String key, Class<T> targetType, T defaultValue){
        return env.getProperty(key, targetType, defaultValue);
    }
}
