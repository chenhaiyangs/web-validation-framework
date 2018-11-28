package com.web.validation.boot.test;

import com.web.validation.boot.annotation.ValidAndProcessEnabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ValidAndProcessEnabled
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
public class SpringBootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestApplication.class,args);
    }
}
