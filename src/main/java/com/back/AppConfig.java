package com.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AppConfig {

    @Autowired
    @Lazy // with @Lazy, the proxy is injected and self-invocation works
    private AppConfig self;

    @Bean
    public ApplicationRunner myApplicationRunner(){
        return args -> {
            this.work1();
            this.work2();

            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        System.out.println("work1");
    }

    @Transactional
    public void work2() {
        System.out.println("work2");
    }

}
