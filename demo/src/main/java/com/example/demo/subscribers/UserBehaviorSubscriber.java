package com.example.demo.subscribers;

import com.didiglobal.ehr.eventbus.annotation.EventSubscriber;
import com.example.demo.event.UserEntryEvent;
import com.example.demo.event.UserLeaveEvent;
import lombok.extern.slf4j.Slf4j;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@EventSubscriber
@Slf4j
//用户行为事件消费者
public class UserBehaviorSubscriber {
    @Subscribe()
    public void userLeaveSubscriber(UserEntryEvent event) {
        log.info("get userLeaveEvent userName:{}", event.getUserName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 1)
    public void userEntrySubscriber(UserLeaveEvent event) {
        log.info("get userEntryEvent userName:{}", event.getUserName());
    }
}
