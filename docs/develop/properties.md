---
title: 配置文件
lang: zh-CN
---
# 配置文件
框架配置都会放到 properties 或 yml配置文件种， prefix 为 fanxing.authentication
## 全部配置

|名称        |说明                           |
|:----------|:-------------------------------|
|*fanxing.authentication.verify-authority-handler-impl*  |认证传权校验接口的实现类路径
|*fanxing.authentication.authentication-failure-handler-impl*  |认证校验失败接口的实现类路径
|*fanxing.authentication.authentication-exception-handler-impl*  |校验异常处理接口的实现类路径
|*fanxing.authentication.authentication-successful-impl*  |认证校验成功接口的实现类路径

## 配置示例
```properties
fanxing.authentication.verify-authority-handler-impl=com.example.fanxingsecuritydemo.handler.VerifyAuthorityImpl
fanxing.authentication.authentication-failure-handler-impl=com.example.fanxingsecuritydemo.handler.AuthenticationFailureImpl
fanxing.authentication.authentication-exception-handler-impl=com.example.fanxingsecuritydemo.handler.AuthenticationExceptionImpl
fanxing.authentication.authentication-successful-impl=com.example.fanxingsecuritydemo.handler.AuthenticationsuccessfuImpl
```

:::tip
IDEA/ECLIPSE有提示
:::
