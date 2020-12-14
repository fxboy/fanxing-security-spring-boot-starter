# fanxing-security-spring-boot-starter

码云： https://gitee.com/fxyun/fanxing-security-spring-boot-starter

GIthub：https://github.com/fxboy/fanxing-security-spring-boot-starter


文档： https://git.fanxing.live



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

```xml
<dependency>
  <groupId>live.fanxing</groupId>
  <artifactId>fanxing-security-spring-boot-starter</artifactId>
  <version>1.3.6</version>
</dependency>
```



推荐环境：

JDK 1.8
	
Springboot 2.4.0



