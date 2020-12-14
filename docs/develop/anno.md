---
title: 注解
lang: zh-CN
---
# 注解
 既然作为校验权限的一个框架，那么就需要使用某种方式给路径上添加权限，本文讲解两个用与设置权限的注解
## HasAuthRole

给路径添加访问角色，操作权限，提交方式

作用范围： 类（Type），方法（Method）

 
|参数        |类型                            |注释                             |
|:----------|:-------------------------------|:-------------------------------|
|*roles*  |String[]|给路径设置角色|
|*permissions*  |String[]|给路径设置操作权限|
|*method*  |String[]|AUTO是拦截所有的提交方式，其他提交方式可以自定义，要大写例如 POST,GET|

```java
@HasAuthRole(roles = {"admin","dept"},permissions = {"select","delete"},method="AUTO")
```

## AllCalibrations

是否开启完全匹配，加上这个注解会使校验操作权限的时候，会校验用户是否包含了所有的操作权限

作用范围： 类（Type），方法（Method）

|参数        |类型                            |注释                             |
|:----------|:-------------------------------|:-------------------------------|
|*permission*  |boolean|是否开启 操作权限的完全匹配，默认为开启|


```java 
    @AllCalibrations
    @HasAuthRole(roles = "admin",permissions = {"select","delete"},method="AUTO")
    @RequestMapping("/test")
    public String test(){
        return "成功";
    }
```
