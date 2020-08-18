package com.example.demo;

import com.didiglobal.ehr.eventbus.SubscriberRegister;
import com.didiglobal.ehr.eventbus.annotation.EventSubscriber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication{

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        SubscriberRegister.register(context);
    }
}
