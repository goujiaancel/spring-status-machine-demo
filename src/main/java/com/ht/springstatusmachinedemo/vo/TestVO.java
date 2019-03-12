package com.ht.springstatusmachinedemo.vo;

import com.ht.springstatusmachinedemo.enums.Events;
import lombok.Getter;
import lombok.Setter;

public class TestVO {
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private Events events;

}
