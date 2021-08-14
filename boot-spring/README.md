

### Spring Boot 源码

---
1. [Spring Boot 启动流程源码分析](https://www.cnblogs.com/java-chen-hao/p/11829344.html#_label0)
2. [Spring Boot Bean的加载流程](https://blog.csdn.net/qq_41893274/article/details/108934028) 

### 基本工作原理

#### 1. SpringApplication 类初始化过程

##### 1.1 SpringBoot项目的mian函数

常规的这个主类如下图所示，我们一般会这样去写。

```java
@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class, args);
    }
}
```

在这个类中需要关注的是

- @SpringBootApplication
- SpringApplication.run()

关于 @SpringBootApplication 注解，在后面分析SpringBoot自动装配的章节会展开去分析。

本章节中我们需要关注的就是 SpringApplication.run() 方法。

查看run()方法的实现，如下面代码所示，我们发现其实其首先是创建了 SpringApplication 的实例，然后调用了 SpringApplication 的run()方法，那本章我们关注的就是 SpringApplication 创建实例的过程。

```java
public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
    return run(new Class[]{primarySource}, args);
}

public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
    // 创建实例
    return (new SpringApplication(primarySources)).run(args);
}
```

##### 1.2 SpringApplication() 构造方法

继续查看源码， SpringApplication 实例化过程，首先是进入一个参数的构造方法，最终回来到两个参数的构造方法。

```java
public SpringApplication(Class<?>... primarySources) {
    this((ResourceLoader)null, primarySources);
}

public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    this.sources = new LinkedHashSet();
    this.bannerMode = Mode.CONSOLE;
    this.logStartupInfo = true;
    this.addCommandLineProperties = true;
    this.addConversionService = true;
    this.headless = true;
    this.registerShutdownHook = true;
    this.additionalProfiles = new HashSet();
    this.isCustomEnvironment = false;
    this.lazyInitialization = false;
    this.resourceLoader = resourceLoader;
    Assert.notNull(primarySources, "PrimarySources must not be null");
    this.primarySources = new LinkedHashSet(Arrays.asList(primarySources));
    
    //推断应用类型，后面会根据类型初始化对应的环境。常用的一般都是servlet环境
    this.webApplicationType = WebApplicationType.deduceFromClasspath();
    
    //初始化classpath下 META-INF/spring.factories中已配置的ApplicationContextInitializer
    this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));
    
    //初始化classpath下所有已配置的 ApplicationListener
    this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class));
    
    //根据调用栈，推断出 main 方法的类名
    this.mainApplicationClass = this.deduceMainApplicationClass();
}
```

##### 1.3 deduceFromClasspath() 判断应用类型

> ```java
> this.webApplicationType = WebApplicationType.deduceFromClasspath();
> ```

```java
public enum WebApplicationType {
    NONE,
    SERVLET,
    REACTIVE;

    private static final String[] SERVLET_INDICATOR_CLASSES = new String[]
    {"javax.servlet.Servlet", "org.springframework.web.context.ConfigurableWebApplicationContext"};
    private static final String WEBMVC_INDICATOR_CLASS = "org.springframework.web.servlet.DispatcherServlet";
    private static final String WEBFLUX_INDICATOR_CLASS = "org.springframework.web.reactive.DispatcherHandler";
    private static final String JERSEY_INDICATOR_CLASS = "org.glassfish.jersey.servlet.ServletContainer";
    private static final String SERVLET_APPLICATION_CONTEXT_CLASS = "org.springframework.web.context.WebApplicationContext";
    private static final String REACTIVE_APPLICATION_CONTEXT_CLASS = 
        "org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext";
/**
 * 判断 应用的类型
 * NONE: 应用程序不是web应用，也不应该用web服务器去启动
 * SERVLET: 应用程序应作为基于servlet的web应用程序运行，并应启动嵌入式servlet web（tomcat）服务器。
 * REACTIVE: 应用程序应作为 reactive web应用程序运行，并应启动嵌入式 reactive web服务器。
 * @return
 */
  static WebApplicationType deduceFromClasspath() {
        if (ClassUtils.isPresent("org.springframework.web.reactive.DispatcherHandler", (ClassLoader)null) &&
             // classpath下必须存在org.springframework.web.reactive.DispatcherHandler
            !ClassUtils.isPresent("org.springframework.web.servlet.DispatcherServlet", (ClassLoader)null) &&
            !ClassUtils.isPresent("org.glassfish.jersey.servlet.ServletContainer", (ClassLoader)null)) {
            return REACTIVE;
        } else {
            String[] var0 = SERVLET_INDICATOR_CLASSES;
            int var1 = var0.length;

            for(int var2 = 0; var2 < var1; ++var2) {
                String className = var0[var2];
                if (!ClassUtils.isPresent(className, (ClassLoader)null)) {
                    return NONE;
                }
            }
            // classpath环境下存在javax.servlet.Servlet或者org.springframework.web.context.ConfigurableWebApplicationContext
            return SERVLET;
        }
    }
}
```

返回类型是WebApplicationType的枚举类型， WebApplicationType 有三个枚举，三个枚举的解释如其中注释

具体的判断逻辑如下：

- WebApplicationType.REACTIVE classpath下存在org.springframework.web.reactive.DispatcherHandler
- WebApplicationType.SERVLET classpath下存在javax.servlet.Servlet或者org.springframework.web.context.ConfigurableWebApplicationContext
- WebApplicationType.NONE 不满足以上条件。

##### 1.4 setInitializers() 

> this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));

初始化classpath下 META-INF/spring.factories中已配置的ApplicationContextInitializer。

```java
private <T> Collection<T> getSpringFactoriesInstances(Class<T> type) {
    return this.getSpringFactoriesInstances(type, new Class[0]);
}

// 通过指定的classloader 从META-INF/spring.factories获取指定的Spring的工厂实例
private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
    ClassLoader classLoader = this.getClassLoader();
    
    // Use names and ensure unique to protect against duplicates
    //通过指定的classLoader从 META-INF/spring.factories 的资源文件中，
    //读取 key 为 type.getName() 的 value
    Set<String> names = new LinkedHashSet(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
    
    //创建Spring工厂实例
    List<T> instances = this.createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
    
    //对Spring工厂实例排序（org.springframework.core.annotation.Order注解指定的顺序）
    AnnotationAwareOrderComparator.sort(instances);
    return instances;
}
```

看看 getSpringFactoriesInstances 都干了什么，看源码，有一个方法很重要 loadFactoryNames() 这个方法很重要，这个方法是spring-core中提供的从META-INF/spring.factories中获取指定的类（key）的同一入口方法。

在这里，获取的是key为 org.springframework.context.ApplicationContextInitializer 的类。

debug看看都获取到了哪些

<img src="https://img2018.cnblogs.com/blog/1635748/201906/1635748-20190606214803226-673240352.png" alt="img" style="zoom:67%;" /> 

上面说了，是从classpath下 META-INF/spring.factories中获取，我们验证一下：

<img src="https://img2018.cnblogs.com/blog/1635748/201906/1635748-20190606220223952-321766434.png" alt="img" style="zoom: 67%;" /> 

<img src="https://img2018.cnblogs.com/blog/1635748/201906/1635748-20190606220205113-514166696.png" alt="img" style="zoom:67%;" /> 

发现在上图所示的两个工程中找到了debug中看到的6条结果。 ApplicationContextInitializer 是Spring框架的类, 这个类的主要目的就是在  ConfigurableApplicationContext 调用refresh()方法之前，回调这个类的initialize方法。通过 ConfigurableApplicationContext 的实例获取容器的环境Environment，从而实现对配置文件的修改完善等工作。

关于怎么实现自定义的 ApplicationContextInitializer 请看我的另一篇专门介绍该类的[博客](https://www.cnblogs.com/hello-shf/p/10987360.html)。

##### 1.5 setListeners()

初始化classpath下 META-INF/spring.factories中已配置的 ApplicationListener。

ApplicationListener 的加载过程和上面的 ApplicationContextInitializer 类的加载过程是一样的。不多说了，至于 ApplicationListener 是spring的事件监听器，典型的观察者模式，通过 ApplicationEvent 类和 ApplicationListener 接口，可以实现对spring容器全生命周期的监听，当然也可以自定义监听事件。为了梳理springboot的启动流程在这里先不说这个了。后面有时间的话再介绍。

关于ApplicationContextInitializer的详细介绍请看[SpringBoot之ApplicationContextInitializer的理解和使用](https://www.cnblogs.com/hello-shf/p/10987360.html) 

##### 1.6  小结

关于 SpringApplication 类的构造过程，到这里我们就梳理完了。纵观 SpringApplication 类的实例化过程，我们可以看到，合理的利用该类，我们能在spring容器创建之前做一些预备工作，和定制化的需求。

比如：

- 自定义SpringBoot的Banner
- 自定义事件监听器
- 在容器refresh之前通过自定义 ApplicationContextInitializer 修改配置一些配置或者获取指定的bean都是可以的。

相关文章

1. https://www.cnblogs.com/hello-shf/p/10976646.html

### 问题梳理


#### 1.1 Spring 主要问题梳理

---
-  [Spring循环依赖的问题](https://mp.weixin.qq.com/s?__biz=MzU2MTI4MjI0MQ==&mid=2247492540&idx=2&sn=a09274b0a49013c3fec6ff34e2a3d10c&chksm=fc798e12cb0e070495f143137069ec2218540675bff36bae158b74503bf9fd807081df35e57c&mpshare=1&scene=23&srcid=0727EPem5cCkJWEMPiTTPseb&sharer_sharetime=1595803362634&sharer_shareid=69531c8a07c3d5e7933f63aeca2a98ef#rd)    

> 请讲一讲Spring中的循环依赖       

   1. 什么是循环依赖？
   2. 什么情况下循环依赖可以被处理？
   3. Spring 如何解决循环依赖？ 





相关文章

1. https://www.cnblogs.com/hello-shf/p/10976646.html