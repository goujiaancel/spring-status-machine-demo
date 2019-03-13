package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.configure.listener.OrderPersistChangeListener;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@Configuration
public class OrderPersistHandlerConfig {
    @Autowired
    private StateMachine<String,String> stateMachine;
    @Autowired
    private OrderPersistChangeListener orderPersistChangeListener;

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler(){
        PersistStateMachineHandler handler = new PersistStateMachineHandler(stateMachine);
        handler.addPersistStateChangeListener(orderPersistChangeListener);
        return handler;
    }



}
