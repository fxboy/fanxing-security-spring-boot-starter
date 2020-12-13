package live.fanxing.authentication.handler;

import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
public interface TokenAuthenticationFailureHandler {
    void tokenAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, ProceedingJoinPoint proceedingJoinPoint) throws Throwable ;
}
