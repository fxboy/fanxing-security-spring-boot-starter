package live.fanxing.security.handler;

import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationSuccessfulHandler {
    Object AuthenticationSuccessful(HttpServletRequest request, HttpServletResponse response,ProceedingJoinPoint proceedingJoinPoint) throws Throwable ;
}
