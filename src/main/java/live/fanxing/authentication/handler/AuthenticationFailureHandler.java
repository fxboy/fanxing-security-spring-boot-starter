package live.fanxing.authentication.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationFailureHandler {
    void authenticationFailure(HttpServletRequest request, HttpServletResponse response);
}
