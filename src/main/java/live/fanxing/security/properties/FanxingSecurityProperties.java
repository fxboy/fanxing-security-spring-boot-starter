package live.fanxing.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fanxing.security")
public class FanxingSecurityProperties {
    private String VerifyAuthorityImpl;
    private String ToKenKey;
    private String AuthenticationFailureImpl;
    private String AuthenticationSuccessfulImpl;
    private String TokenAuthenticationFailureImpl;

    public String getAuthenticationFailureImpl() {
        return AuthenticationFailureImpl;
    }

    public void setAuthenticationFailureImpl(String authenticationFailureImpl) {
        AuthenticationFailureImpl = authenticationFailureImpl;
    }

    public String getAuthenticationSuccessfulImpl() {
        return AuthenticationSuccessfulImpl;
    }

    public void setAuthenticationSuccessfulImpl(String authenticationSuccessfulImpl) {
        AuthenticationSuccessfulImpl = authenticationSuccessfulImpl;
    }

    public String getVerifyAuthorityImpl() {
        return VerifyAuthorityImpl;
    }

    public void setVerifyAuthorityImpl(String verifyAuthorityImpl) {
        VerifyAuthorityImpl = verifyAuthorityImpl;
    }

    public String getToKenKey() {
        return ToKenKey;
    }

    public void setToKenKey(String toKenKey) {
        ToKenKey = toKenKey;
    }

    public String getTokenAuthenticationFailureImpl() {
        return TokenAuthenticationFailureImpl;
    }

    public void setTokenAuthenticationFailureImpl(String tokenAuthenticationFailureImpl) {
        TokenAuthenticationFailureImpl = tokenAuthenticationFailureImpl;
    }
}
