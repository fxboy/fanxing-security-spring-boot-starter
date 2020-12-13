package live.fanxing.util;

import live.fanxing.authentication.annotation.AllCalibrations;
import live.fanxing.authentication.annotation.HasAuthRole;
import live.fanxing.authentication.entity.Authentications;
import live.fanxing.authentication.enums.AnnotationType;
import live.fanxing.authentication.exception.NotFoundHandlerImplException;
import live.fanxing.authentication.exception.PermissionTypeExcetion;
import live.fanxing.authentication.exception.RolesLengthExcetion;
import live.fanxing.authentication.exception.AuthenticationException;
import live.fanxing.authentication.handler.AuthenticationFailureHandler;
import live.fanxing.authentication.handler.AuthenticationSuccessfulHandler;
import live.fanxing.authentication.handler.VerifyAuthorityHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedArrayType;
import java.util.Arrays;
import java.util.List;

/**
 *  用来校验权限的工具类
 *
 * */

public class VerificationUtils {

    // 开发者传权
    private String VerifyAuthorityImpl;

    // 鉴权失败
    private String AuthenticationFailureImpl;

    // 鉴权成功
    private String AuthenticationSuccessfulImpl;

    // 抛出异常
   // private String AuthenticationExceptionImpl;

    public VerificationUtils(String verifyAuthorityImpl, String authenticationFailureImpl, String authenticationSuccessfulImpl) {
        VerifyAuthorityImpl = verifyAuthorityImpl;
        AuthenticationFailureImpl = authenticationFailureImpl;
        AuthenticationSuccessfulImpl = authenticationSuccessfulImpl;

    }

    public boolean authRoleCheck(HttpServletRequest request,HttpServletResponse response,ProceedingJoinPoint proceedingJoinPoint, AnnotationType annotationType) throws NotFoundHandlerImplException, IllegalAccessException, InstantiationException, ClassNotFoundException,  RolesLengthExcetion, PermissionTypeExcetion, AuthenticationException {
        HasAuthRole hasAuthRole = null;
        AllCalibrations allCalibrations = null;
        boolean f = false;
        //获取用户权限内容
        // 反射过来校验权限的实现类，并实现其接口方法,这个接口实现很重要，这里是您验证权限的实现类
        if(this.VerifyAuthorityImpl == null){
            throw new NotFoundHandlerImplException("VerifyAuthorityImpl not found, please configure the path of the implementation class in the configuration file");
        }
        Authentications userAuthentications = ((VerifyAuthorityHandler) reflex(this.VerifyAuthorityImpl)).verifyAuthority(request); //获得用户的权限
        //获取注解上权限
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        hasAuthRole = annotationType.getAnnontationType()?proceedingJoinPoint.getTarget().getClass().getAnnotation(HasAuthRole.class):signature.getMethod().getAnnotation(HasAuthRole.class);  //类上的注解
        allCalibrations =annotationType.getAnnontationType()?proceedingJoinPoint.getTarget().getClass().getAnnotation(AllCalibrations.class):signature.getMethod().getAnnotation(AllCalibrations.class);  //类上的注解

        boolean all = false; // 是否全部匹配权限可操作或者全部的提交方式
        if(allCalibrations != null){
            all = true;
        }

        // 判断注解上的method是否为AUTO
        List methodList = Arrays.asList(hasAuthRole.method());

        if(methodList.contains("AUTO")){
            f = true;
        }

        if(!f){
            String method= request.getMethod();  //获取提交方式
            //如果经过上方的校验，不为true则是不包含auto；开始判断 提交方式
            if(methodList.contains(method)){
                f=true;
            }
        }


        //开始校验权限
        List roleList =Arrays.asList(hasAuthRole.roles());
        List permissionList = Arrays.asList(hasAuthRole.permissions());

        List<String> adRoles = userAuthentications.hasRole(roleList);
        f = adRoles.size() == 0?false:true;
        if(!all && f){

            f = false;
            for (String adRole : adRoles) {
                if(userAuthentications.hasAnyPermission(permissionList,adRole)){
                    f = true;
                }
            }
        }

        if(all && f){
            f = false;
            for (String adRole : adRoles) {
                if(userAuthentications.hasAllPermission(permissionList,adRole)){
                    f = true;
                }
            }
        }

        if(!f){
            if(this.AuthenticationFailureImpl != null){
                AuthenticationFailureHandler authenticationFailureHandler = (AuthenticationFailureHandler) reflex(this.AuthenticationFailureImpl);
                authenticationFailureHandler.authenticationFailure(request,response);
                return false;
            }else{
                // 从 1.3.2 版本开始，我们取消了默认的权限校验失败的返回内容，需要由您来配置
                throw new NotFoundHandlerImplException("AuthenticationFailureimpl not found, please configure the path of the implementation class in the configuration file");
            }
        }

        if(this.AuthenticationSuccessfulImpl != null){
            AuthenticationSuccessfulHandler authenticationSuccessfulHandler = (AuthenticationSuccessfulHandler) reflex(this.AuthenticationSuccessfulImpl);
            authenticationSuccessfulHandler.authenticationSuccessful(request,response,proceedingJoinPoint);
        }

        return true;
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
