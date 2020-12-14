---
title: 开启校验
lang: zh-CN
---
# 开启校验

## 开启校验

我们通过使用 @EnableFanxingSecurity 注解来开启我们的校验框架

只需要将它放到我们的启动类上方即可使用

```java
@SpringBootApplication
@EnableFanxingSecurity
public class DemosApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemosApplication.class, args);
    }
}

```

