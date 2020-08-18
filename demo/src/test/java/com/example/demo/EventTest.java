package com.example.demo;

import com.didiglobal.ehr.eventbus.EventPublisher;
import com.didiglobal.ehr.eventbus.SubscriberRegister;
import com.example.demo.event.UserEntryEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest()
public class EventTest {
    @Resource
    private EventPublisher eventPublisher;

    @Test
    public void postEvent() {
        //单测中不会执行启动类的main方法，要手动注册一下
        SubscriberRegister.register(SpringContextUtil.getApplicationContext());
        UserEntryEvent userEntryEvent = new UserEntryEvent();
        userEntryEvent.setUserName("bigBoss");
        eventPublisher.post(userEntryEvent);
    }
}
