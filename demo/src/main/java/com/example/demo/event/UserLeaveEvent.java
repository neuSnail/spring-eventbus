package com.example.demo.event;

import com.didiglobal.ehr.eventbus.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserLeaveEvent extends BaseEvent {
    private String userId;
    private String userName;
}
