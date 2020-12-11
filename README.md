# fanxing-security-spring-boot-starter

一个异步请求携带Token校验的简易权限框架，将所有操作都留给开发者，它只管验证当前的Token是否有权限取访问这个接口

Simple token asynchronous verification authority framework, support custom success failure return to the page content

码云： https://gitee.com/fxyun/fanxing-security-spring-boot-starter

GIthub：https://github.com/fxboy/fanxing-security-spring-boot-starter

## 1.开始

```xml
<dependency>
  <groupId>live.fanxing</groupId>
  <artifactId>fanxing-security-spring-boot-starter</artifactId>
  <version>1.0.2</version>
</dependency>
```

## 2. 使用
在启动类上添加 @EnableFanxingSecurity注解来开启，如果您的Controller上没有Hash_ROLE注解，则也不会主动进入

```java
@SpringBootApplication
@EnableFanxingSecurity
public class DemosApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemosApplication.class, args);
    }
}
```

## 3.权限注解使用

将@Hash_ROLE(value="") 放到类上或者方法后，就可以给这个mapping或者这个类中所有的mapping添加上权限，支持 多个权限，类与方法添加不同的权限

```java
@Hash_ROLE({"ADMIN","GUEST"})
@RestController
public class TestController {


    @Hash_ROLE("USER")
    @RequestMapping("/test")
    public String con() throws ClassNotFoundException {
        return "test";
    }
}
```

## 4.Token的校验
会通过获取到header中携带的token，然后传给 Authentication tokenAuthentication(Object Token) 方法中的Token，需要您的类实现VerifyAuthorityHandler接口，并在配置文件添加
```properties
fanxing.security.to-ken-key=token   #token为header中携带token的key
```
```java
public class VerifyAuthorityHandlerImpl implements VerifyAuthorityHandler {
    @Override
    public Authentication tokenAuthentication(Object Token) {
        if(Token == null){
        	throw new TokenAuthenticationFailedException("Token 不能为空");
        }
        List abc = new ArrayList<>();
        abc.add("ADMIN");
        abc.add("USER");
        return new Authentication(abc);
    }
}
```

## 5.权限校验成功
```properties
# 您的实现类的包路径 例如fanxing.security.authentication-successful-impl=com.example.demos.security.authenticationsuccessfulimpl
fanxing.security.authentication-successful-impl=
```

当权限校验成功后，如果您在配置文件中添加了此文件后，它会在放行前执行一些操作

## 6.权限校验失败

```properties
# 实现类的包路径
fanxing.security.authentication-failure-impl=
```

默认的权限校验失败会打印到浏览器上 txt类型的权限校验失败，如果您要更改成自己的，那么就需要配置这个文件后在实现类中进行修改

## 7.Token不正确

用户的token不一定会正确或者是失效，我们可以抛出TokenAuthenticationFailedException异常后，加入一下配置文件并实现接口，就可以在实现的类中写不正确要进行的操作以及要返回什么

```properties
# 实现类的包路径
fanxing.security.token-authentication-failure-impl=
```



----

## 1.Start

>The Maven repository has not been submitted yet. You can clone the source code and install it locally using MVN install

```xml
<dependency>
    <groupId>live.fanxing</groupId>
    <artifactId>fanxing-security-spring-boot-starter</artifactId>
    <version>1.0.2</version>
</dependency>
```



## 2. Use

Add the @ enable fanxingsecurity annotation on the startup class to enable it. If there is no hash on your controller_ The role annotation will not be entered

```java
@SpringBootApplication
@EnableFanxingSecurity
public class DemosApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemosApplication.class, args);
    }
}
```




## 3. Use of permission annotation



Will @ hash_ After role (value =) is put on a class or method, you can add permissions to this mapping or all mapping in this class. Multiple permissions are supported, and different permissions are added to classes and methods

```java
@Hash_ROLE({"ADMIN","GUEST"})
@RestController
public class TestController {


    @Hash_ROLE("USER")
    @RequestMapping("/test")
    public String con() throws ClassNotFoundException {
        return "test";
    }
}
```



## 4. Token verification

By getting the token carried in the header and passing it to the token in the authentication tokenauthentication (object token) method, your class needs to implement verifyauthorityhandler interface and add it in the configuration file

```properties

fanxing.security.to -Ken key = token ා token is the key carrying the token in the header

```

```java
public class VerifyAuthorityHandlerImpl implements VerifyAuthorityHandler {
    @Override
    public Authentication tokenAuthentication(Object Token) {
        if(Token == null){
        	throw new TokenAuthenticationFailedException("Token 不能为空");
        }
        List abc = new ArrayList<>();
        abc.add("ADMIN");
        abc.add("USER");
        return new Authentication(abc);
    }
}
```



## 5. Authority verification succeeded

```properties

#The package path of your implementation class, such as fanxing.security.authentication -successful-impl= com.example.demos . security.authenticationsuccessfulimpl

fanxing.security.authentication -successful-impl=

```



After the permission verification is successful, if you add this file to the configuration file, it will perform some operations before release



## 6. Permission verification failed



```properties

#Implement the package path of the class

fanxing.security.authentication -failure-impl=

```



If you want to change it to your own, you need to configure this file and modify it in the implementation class



## 7. Incorrect token



The user's token may not be correct or invalid. We can throw the tokenauthenticationfailedexception exception, add a configuration file and implement the interface, then we can write the incorrect operation and what to return in the implemented class



```properties

#Implement the package path of the class

fanxing.security.token -authentication-failure-impl=

```

