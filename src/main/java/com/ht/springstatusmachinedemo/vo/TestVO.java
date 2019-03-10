package com.ht.springstatusmachinedemo.vo;

import com.ht.springstatusmachinedemo.enums.Events;

public class TestVO {
    private String id;
    private Events events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }
}
