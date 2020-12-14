---
title: 实现接口
lang: zh-CN
---
# 实现接口
 我们为每一个操作都提供了一个接口，您只需要将这些接口类实现然后通过配置文件告诉框架,当触发一些操作时，将会执行您提供的接口类，您实现的接口需要在配置文件内添加[properties文档](properties#properties)

## 全部接口
在开始我们说过，框架仅用于角色和操作权限、提交方式等校验，并不干涉您的其他操作，例如，获取用户角色或操作权限是用token来获取还是cookie或者session，我们都不做任何干涉，我们只提供request让您来获取。
|名称        |接口                            |
|:----------|:-------------------------------|
|*认证传权校验*  |VerifyAuthorityHandler|
|*认证校验成功*  |AuthenticationSuccessfulHandler|
|*认证校验失败*    |AuthenticationFailureHandler|
|*校验异常处理*  |AuthenticationExceptionHandler|

## 认证传权校验
认证传权，也就是我们判断角色权限最重要的一个操作，您可以实现此接口后，在方法内写您来获取角色和权限的操作方法，并使用 Authentications 类保存roles和permission
```java
Authentications verifyAuthority(HttpServletRequest request) throws AuthenticationException;
```

### 参数

|参数        |类型                            |注释                             |
|:----------|:-------------------------------|:-------------------------------|
|*request*  |HttpServletRequest|客户端的请求,当用户发出请求后，如果被拦截到权限校验，则会执行这个方法，并把当时的请求对象带过来|

### 返回
|返回类型        |注释                            |
|:----------|:-------------------------------|
|*Authentications*  |权限保存对象，具体请看 [Authentications文档](Object#Authentications)


### 使用示例
```java
public class VerifyAuthorityImpl implements VerifyAuthorityHandler {
    @Override
    public Authentications tokenAuthentication(HttpServletRequest request) throws TokenVerificationFailedException {
        //请在此处写您获取roles和permissions的操作
        
        //将获取到的roles和permissions保存到Authentications，并将其返回
        Authentications authentications  = new Authentications();
        authentications.add(role1,permission1);
        return authentications;
    }
}
```

## 认证校验成功
校验成功后，是否要执行一些操作，例如 访问日志或者续签等操作
```java
 void authenticationSuccessful(HttpServletRequest request, HttpServletResponse response,ProceedingJoinPoint proceedingJoinPoint);
```

### 参数

|参数        |类型                            |注释                             |
|:----------|:-------------------------------|:-------------------------------|
|*request*  |HttpServletRequest|客户端的请求,当用户发出请求后，如果被拦截到权限校验，则会执行这个方法，并把当时的请求对象带过来|
|*response*  |HttpServletResponse|响应，成功后可以直接通过这个转发页面或者进行其他操作，如果是打印的话，会在获取的内容前添加打印的内容|
|*proceedingJoinPoint*  |ProceedingJoinPoint|环绕通知带过来的对象，可以使用这个对象获取到拦截的类等，具体看Spring Aop的文档吧|


### 使用示例
```java

```

## 认证校验失败
认证校验失败后，需要提示到页面的内容，如果是异步，直接打印json就行了，如果是同步可以直接转发到一个新页面
```java
void authenticationFailure(HttpServletRequest request, HttpServletResponse response);
```

### 参数

|参数        |类型                            |注释                             |
|:----------|:-------------------------------|:-------------------------------|
|*request*  |HttpServletRequest|客户端的请求,当用户发出请求后，如果被拦截到权限校验，则会执行这个方法，并把当时的请求对象带过来|
|*response*  |HttpServletResponse|响应，成功后可以直接通过这个转发页面或者进行其他操作，如果是打印的话，会在获取的内容前添加打印的内容|


### 使用示例
```java

```
## 校验异常处理
校验异常处理，不校验java自带的和框架内一些特定的运行时异常，只会拦截自己手动抛出的AuthenticationException异常

[AuthenticationException](../exception/AuthenticationException)
```java
void authenticationException(HttpServletRequest request, HttpServletResponse response, ProceedingJoinPoint proceedingJoinPoint,Exception ex);
```

|参数        |类型                            |注释                             |
|:----------|:-------------------------------|:-------------------------------|
|*request*  |HttpServletRequest|客户端的请求,当用户发出请求后，如果被拦截到权限校验，则会执行这个方法，并把当时的请求对象带过来|
|*response*  |HttpServletResponse|响应，成功后可以直接通过这个转发页面或者进行其他操作，如果是打印的话，会在获取的内容前添加打印的内容|
|*proceedingJoinPoint*  |ProceedingJoinPoint|环绕通知带过来的对象，可以使用这个对象获取到拦截的类等，具体看Spring Aop的文档吧|
|*ex*  |Exception|把抛出的异常对象传过来|


### 使用示例
```java

```


