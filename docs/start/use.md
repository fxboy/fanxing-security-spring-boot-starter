---
title: 准备
lang: zh-CN
---

# 准备 
fanxing-security-spring-boot-starter
> 支持同步 异步，或者其他方法的权限

因为Spring Security学习成本高，而且现如今项目都是多元化登录的方式，例如账号密码，手机验证码，社交登录：QQ，百度，微博，github等，由于没有一个很好的类将它们全部抽象出来，同时因为使用Ant Design的权限校验得到启发
，做一个专门管理路径角色和操作权限校验的框架提供使用。

这个框架现在支持 roles（角色），permissions（操作权限），Methods（提交方式）来校验权限

Roles： 支持多个角色

Permissions： 一个角色可以支持多个操作权限
Method： 
- AUTO 全部拦截
- POST 只拦截POST
- GET 只拦截GET
- ......

当请求的用户拥有 一个admin的角色时，但是它只有 select的操作权限，而一个路径它规定了admin角色和delete权限，所以这个请求就会被拦截，鉴权失败
而Method的拦截方式，如果请求的方法是GET，而路径设置的是 POST，不论角色和权限是否通过，那么都会跳到鉴权失败，我也不知道有什么用，但是有总比没有好


本文档适合  fanxing-security-spring-boot-starter 版本 >= 1.3.5

推荐环境：

JDK 1.8
	
Springboot 2.4.0


:::tip
fanxing-security-spring-boot-starter 是 一个精简版的安全框架，它将 权限校验给分离了出来，支持多种校验方式，登录和防攻击的问题，需要您自己来实现，框架内提供了3个接口来让框架实现更多的样式。
:::
:::warning
如果出现依赖冲突，请将您的Springboot版本换成 2.4.0，或者您自己来找到解决当前冲突的办法。
:::

