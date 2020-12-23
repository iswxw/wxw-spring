### Boot-starter 自定义一个starter



### 重要注解

-   **@ConditionalOnProperty 注解** 

  控制 @Configuration 是否生效。简单来说也就是我们可以通过在yml配置文件中控制 @Configuration 注解的配置类是否生效。

- @EnableConfigurationProperties 注解

  用来开启对3步骤中 @ConfigurationProperties 注解配置Bean的支持。也就是@EnableConfigurationProperties注解告诉Spring Boot 能支持@ConfigurationProperties。

**相关文章**

1. [自定义 spring boot starter ](https://www.cnblogs.com/hello-shf/p/10864977.html) 