#### 常见问题

---

1. 分析注解基于SpringMVC的@requestparam和Validate的@Notnull注解有什么区别？    
  
   > 1、顺序应该是requestparam，后@notNull这个   
     2、Notnull判断这个参数是不是为null,@RequestParam判断有没有这个参数   
     3、最关键的是这些验证都是还没有进入controller方法体前执行的