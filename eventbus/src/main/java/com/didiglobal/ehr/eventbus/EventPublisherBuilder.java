package com.didiglobal.ehr.eventbus;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventPublisherBuilder {
    ExecutorService asyncPostExecutor = defaultExecutor();

    EventBus eventBus = defaultEventBus();

    public static EventPublisherBuilder builder() {
        return new EventPublisherBuilder();
    }

    public static EventPublisher createDefault() {
        return EventPublisherBuilder.builder().build();
    }

    private ExecutorService defaultExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4, 8,
                5L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(1000),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return TtlExecutors.getTtlExecutorService(executor);
    }

    private EventBus defaultEventBus() {
        return EventBus.builder()
                .eventInheritance(false)
                .throwSubscriberException(true)
                .executorService(defaultExecutor())
                .build();
    }

    public EventPublisherBuilder asyncPostExecutor(ExecutorService executorService) {
        this.asyncPostExecutor = executorService;
        return this;
    }

    public EventPublisherBuilder EventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        return this;
    }

    public EventPublisher build() {
        return new EventPublisher(eventBus, asyncPostExecutor);
    }
}
