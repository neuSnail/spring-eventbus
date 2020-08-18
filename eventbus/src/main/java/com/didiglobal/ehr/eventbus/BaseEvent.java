package com.didiglobal.ehr.eventbus;

import org.springframework.util.StringUtils;

import java.util.UUID;

abstract public class BaseEvent {
    private String eventId = null;

    protected String getEventId() {
        if (eventId != null) {
            return eventId;
        }
        eventId = UUID.randomUUID().toString();
        return eventId;
    }
}
