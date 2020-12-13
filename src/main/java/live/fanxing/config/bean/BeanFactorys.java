package live.fanxing.config.bean;

import live.fanxing.config.properties.FanxingSecurityProperties;
import live.fanxing.util.VerificationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

    @Configuration
    public class BeanFactorys {

        @Resource
        private FanxingSecurityProperties fanxingSecurityProperties;


    }
