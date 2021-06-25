

### Spring Boot 源码

---
1. [Spring Boot 启动流程源码分析](https://www.cnblogs.com/java-chen-hao/p/11829344.html#_label0)
2. [Spring Boot Bean的加载流程](https://blog.csdn.net/qq_41893274/article/details/108934028)


#### 1.1 Spring 主要问题梳理

---
-  [Spring循环依赖的问题](https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247492540&idx=2&sn=a09274b0a49013c3fec6ff34e2a3d10c&chksm=fc798e12cb0e070495f143137069ec2218540675bff36bae158b74503bf9fd807081df35e57c&mpshare=1&scene=23&srcid=0727EPem5cCkJWEMPiTTPseb&sharer_sharetime=1595803362634&sharer_shareid=69531c8a07c3d5e7933f63aeca2a98ef#rd)    

> 请讲一讲Spring中的循环依赖       
  
   1. 什么是循环依赖？
   2. 什么情况下循环依赖可以被处理？
   3. Spring 如何解决循环依赖？ 