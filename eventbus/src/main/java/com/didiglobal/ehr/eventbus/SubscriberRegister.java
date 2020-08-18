package com.didiglobal.ehr.eventbus;

import com.didiglobal.ehr.eventbus.annotation.EventSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public class SubscriberRegister {
    private static final Logger logger = LoggerFactory.getLogger(SubscriberRegister.class);

    /**
     * 注册事件消费者
     *
     * @param context spring容器上下文
     */
    public static void register(ApplicationContext context) {
        EventPublisher publisher = context.getBean(EventPublisher.class);
        if (publisher == null) {
            throw new RuntimeException("event publisher bean not found");
        }

        Map<String, Object> subscriberMap = context.getBeansWithAnnotation(EventSubscriber.class);
        if (CollectionUtils.isEmpty(subscriberMap)) {
            logger.info("no event subscribers found");
            return;
        }
        subscriberMap.forEach((name, bean) -> {
            publisher.register(bean);
            logger.info("subscriber:{} registered to event bus", name);
        });
    }
}
