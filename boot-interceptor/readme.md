> 一、SpringBoot 拦截器应用
1. [拦截并统一处理异常](https://www.cnblogs.com/lvbinbin2yujie/p/10574812.html#type4)     
      1. 异常处理方式一. @ExceptionHandler+@Controller，控制层处理  [1]
      2. 异常处理方式二. 实现HandlerExceptionResolver接口，全局异常处理  [3]
         - 缺点分析：返回JSP、velocity等视图比较方便，JSON、XML需要额外处理
      3. @ControllerAdvice+@ExceptionHandler，全局异常处理   [2]