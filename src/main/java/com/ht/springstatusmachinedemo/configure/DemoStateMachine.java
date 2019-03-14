package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.configure.action.OrderAction;
import com.ht.springstatusmachinedemo.configure.guard.OrderGuard;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author goujia
 * @version Id: DemoStatusMachine.java, v 0.1 2019/3/8 10:13 goujia Exp $
 */

@EnableStateMachine(name = "stateMachine")
@Configuration
@Log
public class DemoStateMachine extends StateMachineConfigurerAdapter<String,String> {
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
    public void configure(StateMachineStateConfigurer<String, String> status) throws Exception {
        Set<String> stringStates = new HashSet<>();
        EnumSet.allOf(States.class).forEach(state -> stringStates.add(state.name()));
        status.withStates().initial(States.UNPAID.name()).end(States.DONE.name()).end(States.CLOSE.name()).states(stringStates);
    }

    /**
     * 配置状态机的状态流转
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        transitions.withExternal().
                source(States.UNPAID.name()).guard(orderGuard).target(States.WAITING_FOR_RECEIVE.name()).action(orderAction).event(Events.PAY.name())
                .and().withExternal()
                .source(States.WAITING_FOR_RECEIVE.name()).target(States.CLOSE.name()).event(Events.CANCEL_ORDER.name())
                .and().withExternal()
                .source(States.WAITING_FOR_RECEIVE.name()).target(States.DONE.name()).event(Events.RECEIVE.name())
        ;
    }
}
