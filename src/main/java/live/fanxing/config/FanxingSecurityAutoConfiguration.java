package live.fanxing.config;


import live.fanxing.authentication.aspect.SecurityService;
import live.fanxing.config.properties.FanxingSecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@ConditionalOnMissingBean(SecurityService.class)
@EnableConfigurationProperties(FanxingSecurityProperties.class)
@Configuration
public class FanxingSecurityAutoConfiguration {
    @Resource
    private FanxingSecurityProperties fanxingSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(SecurityService.class)
    public SecurityService securityService(){
        System.out.println("Fanxing-Security 已经引入");
        return new SecurityService(fanxingSecurityProperties.getVerifyAuthorityImpl(),fanxingSecurityProperties.getAuthenticationFailureImpl(),fanxingSecurityProperties.getAuthenticationSuccessfulImpl(),fanxingSecurityProperties.getTokenAuthenticationFailureImpl());
    }
}
