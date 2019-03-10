package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.awt.*;
import java.util.EnumSet;

/**
 * @author goujia
 * @version Id: DemoStatusMachine.java, v 0.1 2019/3/8 10:13 goujia Exp $
 */
@Configuration
@EnableStateMachine(name = "stateMachine")
public class DemoStatusMachine extends EnumStateMachineConfigurerAdapter<States,Events> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 配置状态机的初始状态
     * @param status
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> status) throws Exception {
        status.withStates().initial(States.UNPAID).states(EnumSet.allOf(States.class));
    }

    /**
     * 配置状态机的状态流转
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal().
                source(States.UNPAID).target(States.WAITING_FOR_RECEIVE).event(Events.PAY)
                .and().withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.CLOSE).event(Events.CANCEL_ORDER)
                .and().withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE).event(Events.RECEIVE)
        ;
    }



}
