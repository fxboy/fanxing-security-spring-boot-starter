package live.fanxing.authentication.aspect;

import live.fanxing.authentication.annotation.HasAuthRole;
import live.fanxing.authentication.entity.Authentications;
import live.fanxing.authentication.enums.Method;
import live.fanxing.authentication.exception.NotFoundHandlerImplException;
import live.fanxing.authentication.handler.AuthenticationFailureHandler;
import live.fanxing.authentication.handler.AuthenticationSuccessfulHandler;
import live.fanxing.authentication.handler.VerifyAuthorityHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;


@Aspect
@Configuration
public class SecurityService{
    private String VerifyAuthorityImpl;
    private String AuthenticationFailureImpl;
    private String AuthenticationSuccessfulImpl;
    private String TokenAuthenticationFailureImpl;
    private Authentications authentications;
    public SecurityService(String verifyAuthorityImpl, String authenticationFailureImpl, String authenticationSuccessfulImpl, String tokenAuthenticationFailureImpl) {
        VerifyAuthorityImpl = verifyAuthorityImpl;
        AuthenticationFailureImpl = authenticationFailureImpl;
        AuthenticationSuccessfulImpl = authenticationSuccessfulImpl;
        TokenAuthenticationFailureImpl = tokenAuthenticationFailureImpl;
        System.out.println("带过来了");
    }

    /**
     * 用来拦截到类上的注解
     * */
    @Around("@within(live.fanxing.authentication.annotation.HasAuthRole)")
    public Object ClasshashRole(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;

            return object = this.authRoleCheck(pjp,2);

    }


    /**
     * 用来拦截到方法上的注解
     * */
    @Around("@annotation(live.fanxing.authentication.annotation.HasAuthRole)")
    public Object MethodhashRole(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;

            return this.authRoleCheck(pjp,2);

    }


    /**
     * 角色以及权限的校验
     * @param type 获取注解的方式 type == 1 获取类上的注解  type == other 获取方法上的注解
     * */
   private Object authRoleCheck(ProceedingJoinPoint proceedingJoinPoint,int type) throws Throwable {
       // 获取Request与Response
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       HttpServletRequest request = attributes.getRequest();
       HttpServletResponse response = attributes.getResponse();
       //获取用户权限内容
            // 反射过来校验权限的实现类，并实现其接口方法,这个接口实现很重要，这里是您验证权限的实现类
            if(this.VerifyAuthorityImpl == null){
                throw new NotFoundHandlerImplException("VerifyAuthorityImpl not found, please configure the path of the implementation class in the configuration file");
            }
            this.authentications = ((VerifyAuthorityHandler) reflex(this.VerifyAuthorityImpl)).tokenAuthentication(request);

       //获取注解上权限
       MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
       HasAuthRole classAnnotation = type==1?proceedingJoinPoint.getTarget().getClass().getAnnotation(HasAuthRole.class):signature.getMethod().getAnnotation(HasAuthRole.class);  //类上的注解

       //获取要校验的提交方式
       boolean requestMethod = false; //是否需要校验

       //如果 调用的提交方式与注解上的提交方式一样，或者注解上提交方式是AUTO，则会进入校验权限与操作
       if(request.getMethod().equals(classAnnotation.method().getMethod())  || classAnnotation.method() == Method.AUTO){
           requestMethod = true;
       }

       //权限不存在
       if(requestMethod && !this.authentications.hasAuthority(classAnnotation.roles(),classAnnotation.permissions())){
           if(this.AuthenticationFailureImpl != null){
               AuthenticationFailureHandler authenticationFailureHandler = (AuthenticationFailureHandler) reflex(this.AuthenticationFailureImpl);
               authenticationFailureHandler.AuthenticationFailure(request,response);
               return null;
           }else{
               // 从 1.3.2 版本开始，我们取消了默认的权限校验失败的返回内容，需要由您来配置
               throw new NotFoundHandlerImplException("AuthenticationFailureimpl not found, please configure the path of the implementation class in the configuration file");
           }
       }

       if(this.AuthenticationSuccessfulImpl != null){
           AuthenticationSuccessfulHandler authenticationSuccessfulHandler = (AuthenticationSuccessfulHandler) reflex(this.AuthenticationSuccessfulImpl);
           authenticationSuccessfulHandler.AuthenticationSuccessful(request,response,proceedingJoinPoint);
       }
       return proceedingJoinPoint.proceed();
    }

    /**
     * 通过反射创建对象
     * @param interfaceImpl 接口的实现类的路径
     * */
    Object reflex(String interfaceImpl) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class classImpl = Class.forName(interfaceImpl);
        Object object = classImpl.newInstance();
        return object;
    }


}
