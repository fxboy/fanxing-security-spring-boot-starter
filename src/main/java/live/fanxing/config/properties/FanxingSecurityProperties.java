package live.fanxing.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fanxing.authentication")
public class FanxingSecurityProperties {

    private String authenticationExceptionHandlerImpl;  //鉴权期间出异常后处理的实现类
    private String authenticationFailureHandlerImpl;   // 鉴权失败后处理的实现类
    private String verifyAuthorityHandlerImpl; //开发者传权校验入口
    private String authenticationSuccessfulImpl; //鉴权成功后处理的实现类
    @Deprecated
    private String userAuthorityExceptionHandlerImpl; //用户校验失败后出现的异常实现类

    public String getAuthenticationExceptionHandlerImpl() {
        return authenticationExceptionHandlerImpl;
    }

    public void setAuthenticationExceptionHandlerImpl(String authenticationExceptionHandlerImpl) {
        this.authenticationExceptionHandlerImpl = authenticationExceptionHandlerImpl;
    }

    public String getAuthenticationFailureHandlerImpl() {
        return authenticationFailureHandlerImpl;
    }

    public void setAuthenticationFailureHandlerImpl(String authenticationFailureHandlerImpl) {
        this.authenticationFailureHandlerImpl = authenticationFailureHandlerImpl;
    }

    public String getVerifyAuthorityHandlerImpl() {
        return verifyAuthorityHandlerImpl;
    }

    public void setVerifyAuthorityHandlerImpl(String verifyAuthorityHandlerImpl) {
        this.verifyAuthorityHandlerImpl = verifyAuthorityHandlerImpl;
    }

    public String getAuthenticationSuccessfulImpl() {
        return authenticationSuccessfulImpl;
    }

    public void setAuthenticationSuccessfulImpl(String authenticationSuccessfulImpl) {
        this.authenticationSuccessfulImpl = authenticationSuccessfulImpl;
    }

    public String getUserAuthorityExceptionHandlerImpl() {
        return userAuthorityExceptionHandlerImpl;
    }

    public void setUserAuthorityExceptionHandlerImpl(String userAuthorityExceptionHandlerImpl) {
        this.userAuthorityExceptionHandlerImpl = userAuthorityExceptionHandlerImpl;
    }
}
