---
title: 异常
lang: zh-CN
---
# 异常
框架中有5个自定义异常,其中token校验失败已经已经弃用，改为了 AuthenticationException

## AuthenticationException
验证中失败异常，主要用于在 校验传权时，用户未登录，token失效，token不存在等等问题，所抛出此异常后，可直接进入鉴权失败处理，如果实现了AuthenticationExceptionHandler接口，
则会将转移到此处来进行处理相应的操作
## NotFoundHandlerImplException
没有找到拦截接口实现类异常，在配置文件中，我们定义了4个配置，只要它不为null的话，在鉴权期间，则会根据这个配置然后反射此实现类，如果没有找到当前类，则会抛出，和ClassNotFoundException一样，但只用于框架中这4个接口
## PermissionTypeExcetion
操作权限类型出现异常，因为操作权限的类型仅支持 String,String[],ArraList< String >,如果传入的类型不是这三种，则会抛出此异常
## RolesLengthExcetion
传入的角色长度为0，也就是在传权的时候，没有传入角色
## TokenVerificationFailedException （已弃用）
原先是1.0.2中校验token出错的异常，由于1.3.5后改为不止可以校验token，所以抛弃了此异常并更换成AuthenticationException
