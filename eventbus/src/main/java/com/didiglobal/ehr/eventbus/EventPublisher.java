package com.didiglobal.ehr.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.*;

@Slf4j
public class EventPublisher {
    private EventBus eventBus;

    private ExecutorService asyncPostExecutor;

    public EventPublisher(EventBus eventBus, ExecutorService asyncPostExecutor) {
        this.eventBus = eventBus;
        this.asyncPostExecutor = asyncPostExecutor;
    }

    /**
     * 注册订阅者
     */
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void post(BaseEvent event) {
        eventBus.post(event);
        log.info("event was post,id:{},name:{}", event.getEventId(), event.getClass().getName());
    }

    /**
     * 在生产者端使用线程池实现异步
     * 注意区分消费者中的 ThreadMode.ASYNC ，后者是在消费端异步
     */
    public void postAsync(BaseEvent event) {
        asyncPostExecutor.execute(() -> eventBus.post(event));
    }
}
