> 一、SpringBoot 拦截器应用
1. [拦截并统一处理异常](https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html#type4)     
      1. 异常处理方式一. @ExceptionHandler+@Controller，控制层处理  [1]
      2. 异常处理方式二. 实现HandlerExceptionResolver接口，全局异常处理  [3]
         - 缺点分析：返回JSP、velocity等视图比较方便，JSON、XML需要额外处理
      3. @ControllerAdvice+@ExceptionHandler，全局异常处理   [2]
      
2. [过滤器的实现]()
      - 实现方式一： [实现springboot中javax.servlet.Filter原生接口的实现]()    
      - 实现方式二： [继承Spring内置的OncePerRequestFilter抽象类](https://www.jianshu.com/p/b2aa7dd346a2)    
      
   > 区别于联系                                                                                                                                                                                   
     - 区别：   
        方式一 实现springboot中javax.servlet.Filter原生接口的实现在Servlet服务器内部进行页面跳转**请求转发** 和 **重定向** 时也会经过过滤器   
        方式二 继承Spring内置的OncePerRequestFilter抽象类【一次请求中只过滤一次】就避免了内部请求过滤，只过滤外部的一些请求
3. [请求转发](https://www.cnblogs.com/wwct/articles/12275815.html)    
  > 1. 请求转发注意事项   

     - 必须使用@Controller而非@RestController   
     - 必须加 @ResponseBody 响应类型                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                      
      
