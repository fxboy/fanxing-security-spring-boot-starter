---
title: 更新日志
lang: zh-CN
---
# 更新日志

> 将从1.3.5版本作为起始版本发布

## 1.3.5


新增 @HasAuthRole 注解 ，去除 @HashRole注解

去除默认的提示信息，改为开发者自定义

去除从Header获取Token

获取角色和操作权限不止从token获取，支持cookie，session等方式

新增permission操作权限校验，一个角色对应一个操作权限，一个路径对应多个（考虑不全 1.3.6将会修改）

新增Authentications

新增Method提交方式校验

## 1.3.6

支持一个角色对多个操作权限，一个路径对应多个操作权限

新增 AuthenticationExceptionHandler接口

Method校验用枚举类改为字符串数组，支持多个Method同时被校验




