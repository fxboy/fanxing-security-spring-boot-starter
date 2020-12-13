package live.fanxing.authentication.aspect;

import live.fanxing.authentication.annotation.HasAuthRole;
import live.fanxing.authentication.entity.Authentications;
import live.fanxing.authentication.enums.AnnotationType;
import live.fanxing.authentication.enums.Method;
import live.fanxing.authentication.exception.NotFoundHandlerImplException;
import live.fanxing.authentication.exception.PermissionTypeExcetion;
import live.fanxing.authentication.exception.RolesLengthExcetion;
import live.fanxing.authentication.exception.AuthenticationException;
import live.fanxing.authentication.handler.AuthenticationExceptionHandler;
import live.fanxing.authentication.handler.AuthenticationFailureHandler;
import live.fanxing.authentication.handler.AuthenticationSuccessfulHandler;
import live.fanxing.authentication.handler.VerifyAuthorityHandler;
import live.fanxing.util.VerificationUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;


@Aspect
@Configuration
public class SecurityAspect {

    @Autowired
    VerificationUtils verificationUtils;
    // 抛出异常
    private String authenticationExceptionImpl;

    // 鉴权失败
    private String authenticationFailureImpl;

    public SecurityAspect(String authenticationExceptionImpl, String authenticationFailureImpl) {
        this.authenticationExceptionImpl = authenticationExceptionImpl;
        this.authenticationFailureImpl = authenticationFailureImpl;
    }

    /**
     * 用来拦截到类上的注解
     * */
    @Around("@within(live.fanxing.authentication.annotation.HasAuthRole)")
    public Object ClasshashRole(ProceedingJoinPoint pjp) {
        return execute(pjp,AnnotationType.TYPE);
    }
    /**
     * 用来拦截到方法上的注解
     * */
    @Around("@annotation(live.fanxing.authentication.annotation.HasAuthRole)")
    public Object MethodhashRole(ProceedingJoinPoint pjp){
        return execute(pjp,AnnotationType.METHOD);
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

    /**
     * 校验权限入口
     * @param annotationType 获取注解的方式  TYPE 为类上的，METHOD 是注解上的
     * */

    Object execute(ProceedingJoinPoint pjp,AnnotationType annotationType) {
        boolean f = false;
        Object o = null;
        // 获取Request与Response
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;character=utf-8");
        try{
            o = pjp.proceed();
            f = verificationUtils.authRoleCheck(request,response,pjp, annotationType);
        } catch (RolesLengthExcetion rolesLengthExcetion) {
            rolesLengthExcetion.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            f = false;
            if(this.authenticationExceptionImpl != null){
                AuthenticationExceptionHandler authenticationExceptionHandler = (AuthenticationExceptionHandler) reflex(this.authenticationExceptionImpl);
                authenticationExceptionHandler.authenticationException(request,response,pjp,e);
                return null;
            }
            AuthenticationFailureHandler authenticationFailureHandler = (AuthenticationFailureHandler) reflex(this.authenticationFailureImpl);
            authenticationFailureHandler.authenticationFailure(request,response);
        } catch (NotFoundHandlerImplException e) {
            e.printStackTrace();
        } catch (PermissionTypeExcetion permissionTypeExcetion) {
            permissionTypeExcetion.printStackTrace();
        }finally {
            return f?o:null;
        }

    }
}
