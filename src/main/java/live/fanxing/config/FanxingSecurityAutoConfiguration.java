package live.fanxing.config;


import live.fanxing.authentication.aspect.SecurityAspect;
import live.fanxing.config.properties.FanxingSecurityProperties;
import live.fanxing.util.VerificationUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@ConditionalOnMissingBean(SecurityAspect.class)
@EnableConfigurationProperties(FanxingSecurityProperties.class)
@Configuration
public class FanxingSecurityAutoConfiguration {
    @Resource
    private FanxingSecurityProperties fanxingSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(SecurityAspect.class)
    public SecurityAspect securityService(){
        System.out.println("Fanxing-Security 已经成功引入了，如有问题请查看文档：http://git.fanxing.live");
        return new SecurityAspect(fanxingSecurityProperties.getAuthenticationExceptionHandlerImpl(),fanxingSecurityProperties.getAuthenticationFailureHandlerImpl());
    }

    @Bean
    public VerificationUtils verificationUtils() {
        return new VerificationUtils(fanxingSecurityProperties.getVerifyAuthorityHandlerImpl(),fanxingSecurityProperties.getAuthenticationFailureHandlerImpl(), fanxingSecurityProperties.getAuthenticationSuccessfulImpl());
    }
}
