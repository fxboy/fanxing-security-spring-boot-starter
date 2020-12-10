package live.fanxing.authentication.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationFailureHandler {
    void AuthenticationFailure(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
