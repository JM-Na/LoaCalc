package jmna.loacalc.feign.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Value("${smilegate.jwt}")
    private String JWT_TOKEN;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate ->{
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json;");
            requestTemplate.header("authorization", "bearer " + JWT_TOKEN);
        };
    }

}
