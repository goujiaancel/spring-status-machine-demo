package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 状态机内存持久化
 */
@Component
public class InMemoryStateMachinePersist implements StateMachinePersist<States,Events,String> {
    private static HashMap<String, StateMachineContext<States, Events>> map = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> context, String contextObj) {
        map.put(contextObj,context);

    }

    @Override
    public StateMachineContext<States, Events> read(String contextObj) {
        return map.get(contextObj);
    }
}
