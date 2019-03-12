package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.configure.action.OrderAction;
import com.ht.springstatusmachinedemo.configure.guard.OrderGuard;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;

/**
 * @author goujia
 * @version Id: DemoStatusMachine.java, v 0.1 2019/3/8 10:13 goujia Exp $
 */

@EnableStateMachine(name = "stateMachine")
@Configuration
@Log
public class DemoStateMachine extends EnumStateMachineConfigurerAdapter<States,Events> {
    @Autowired
    private OrderGuard orderGuard;
    @Autowired
    private OrderAction orderAction;
    public static final String MACHINE_NAME = "stateMachine";

    /**
     * 配置状态机的初始状态
     * @param status
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> status) throws Exception {
        status.withStates().initial(States.UNPAID).end(States.DONE).end(States.CLOSE).states(EnumSet.allOf(States.class));
    }

    /**
     * 配置状态机的状态流转
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal().
                source(States.UNPAID).guard(orderGuard).target(States.WAITING_FOR_RECEIVE).action(orderAction).event(Events.PAY)
                .and().withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.CLOSE).event(Events.CANCEL_ORDER)
                .and().withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE).event(Events.RECEIVE)
        ;
    }
}
