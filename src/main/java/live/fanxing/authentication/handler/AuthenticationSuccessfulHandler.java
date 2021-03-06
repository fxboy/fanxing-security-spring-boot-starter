package live.fanxing.authentication.handler;

import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationSuccessfulHandler {
    void authenticationSuccessful(HttpServletRequest request, HttpServletResponse response,ProceedingJoinPoint proceedingJoinPoint);
}
