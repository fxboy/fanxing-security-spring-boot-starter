package live.fanxing.security;

import live.fanxing.security.aspect.FanxingSecurityRoleAspect;
import live.fanxing.security.properties.FanxingSecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@ConditionalOnMissingBean(FanxingSecurityRoleAspect.class)
@EnableConfigurationProperties(FanxingSecurityProperties.class)
@Configuration
public class FanxingSecurityAutoConfiguration {

    @Resource
    private FanxingSecurityProperties fanxingSecurityProperties;


    @Bean
    @ConditionalOnMissingBean(FanxingSecurityRoleAspect.class)
    public FanxingSecurityRoleAspect fanxingSecurityRoleAspect() {
        return new FanxingSecurityRoleAspect(fanxingSecurityProperties.getVerifyAuthorityImpl(),fanxingSecurityProperties.getAuthenticationFailureImpl(),fanxingSecurityProperties.getAuthenticationSuccessfulImpl(),fanxingSecurityProperties.getTokenAuthenticationFailureImpl(),fanxingSecurityProperties.getToKenKey());
    }
}
