package live.fanxing.security.aspect;

import live.fanxing.security.annotation.Hash_ROLE;
import live.fanxing.security.entity.Authentication;
import live.fanxing.security.exception.TokenAuthenticationFailedException;
import live.fanxing.security.handler.AuthenticationFailureHandler;
import live.fanxing.security.handler.AuthenticationSuccessfulHandler;
import live.fanxing.security.handler.TokenAuthenticationFailureHandler;
import live.fanxing.security.handler.VerifyAuthorityHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Aspect
@Configuration
public class FanxingSecurityRoleAspect {

    private String VerifyAuthorityImpl;
    private String TokenKey;
    private String AuthenticationFailureImpl;
    private String AuthenticationSuccessfulImpl;
    private String TokenAuthenticationFailureImpl;
    List<String> authenticationItems;

    public FanxingSecurityRoleAspect(String VerifyAuthorityImpl,String AuthenticationFailureImpl,String AuthenticationSuccessfulImpl,String TokenAuthenticationFailureImpl,String TokenKey){
        this.VerifyAuthorityImpl = VerifyAuthorityImpl;
        this.TokenKey = TokenKey;
        this.AuthenticationSuccessfulImpl = AuthenticationSuccessfulImpl;
        this.AuthenticationFailureImpl = AuthenticationFailureImpl;
        this.TokenAuthenticationFailureImpl = TokenAuthenticationFailureImpl;

    }

    @Around("@within(live.fanxing.security.annotation.Hash_ROLE)")
    public Object ClasshashRole(ProceedingJoinPoint pjp) throws Throwable {
        return VRole(pjp,1);
    }


    @Around("@annotation(live.fanxing.security.annotation.Hash_ROLE)")
    public Object MethodhashRole(ProceedingJoinPoint pjp) throws Throwable {
        return VRole(pjp,2);
    }


    Object VRole(ProceedingJoinPoint pjp,int type) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        boolean ex = setAuthenticationItems(request);
        if(!ex){
          return tokenFailed(request,response,pjp);
        }
        //获取注解的权限
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Hash_ROLE classAnnotation = type==1?pjp.getTarget().getClass().getAnnotation(Hash_ROLE.class):signature.getMethod().getAnnotation(Hash_ROLE.class);  //类上的注解
        boolean find = false;
        for (String s : classAnnotation.value()) {
            if(this.authenticationItems.indexOf(s) > -1){
                find = true;
                break;
            }
        }


        if(!find){

            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            if(this.AuthenticationFailureImpl != null){
                Class failClass = Class.forName(AuthenticationFailureImpl);
                AuthenticationFailureHandler authenticationFailureHandler = (AuthenticationFailureHandler) failClass.newInstance();
                authenticationFailureHandler.AuthenticationFailure(request, response);
                return null;
            }
            PrintWriter pw = response.getWriter();
            pw.print("您没有权限访问当前页面");
            return null;
        }else{
            if(this.AuthenticationSuccessfulImpl != null){
                Class successClass = Class.forName(AuthenticationSuccessfulImpl);
                AuthenticationSuccessfulHandler authenticationSuccessfulHandler = (AuthenticationSuccessfulHandler) successClass.newInstance();
                return authenticationSuccessfulHandler.AuthenticationSuccessful(request,response,pjp);
            }else{
                return pjp.proceed();
            }

        }

    }
    boolean setAuthenticationItems(HttpServletRequest request ) throws Throwable{
        try{
            if(this.authenticationItems == null){
                Class verifyAuthorityClass = Class.forName(VerifyAuthorityImpl);
                VerifyAuthorityHandler verifyAuthority = (VerifyAuthorityHandler) verifyAuthorityClass.newInstance();
                Authentication authentication = verifyAuthority.tokenAuthentication(request.getHeader(this.TokenKey));

                this.authenticationItems = authentication.getAuthentication();
            }

            return true;
        }catch (TokenAuthenticationFailedException ex){
            return false;
        }
    }


    Object tokenFailed(HttpServletRequest request,HttpServletResponse response,ProceedingJoinPoint pjp) throws Throwable {
        if (this.TokenAuthenticationFailureImpl != null){
            Class tokenAuthenticationClass = Class.forName(this.TokenAuthenticationFailureImpl);
            TokenAuthenticationFailureHandler tokenAuthenticationFailureHandler = (TokenAuthenticationFailureHandler) tokenAuthenticationClass.newInstance();
            tokenAuthenticationFailureHandler.tokenAuthenticationFailure(request,response,pjp);
            return null;
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.print("Token authentication failed");
        return null;
    }
}
