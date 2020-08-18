package com.example.demo;

import com.didiglobal.ehr.eventbus.EventPublisher;
import com.didiglobal.ehr.eventbus.EventPublisherBuilder;
import org.greenrobot.eventbus.EventBus;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class EventBusConfig {
    @Bean
    public EventPublisher eventPublisher() {
        return EventPublisherBuilder.builder()
                .asyncPostExecutor(Executors.newFixedThreadPool(1))
                .EventBus(EventBus.builder()
                        .throwSubscriberException(false)
                        .sendNoSubscriberEvent(true)
                        .executorService(Executors.newCachedThreadPool())
                        .build()
                ).build();
    }
}


