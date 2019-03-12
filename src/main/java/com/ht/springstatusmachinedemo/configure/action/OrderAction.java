package com.ht.springstatusmachinedemo.configure.action;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author goujia
 * @version Id: OrderAction.java, v 0.1 2019/3/12 15:18 goujia Exp $
 */
@Component
public class OrderAction implements Action<States,Events> {

    @Override
    public void execute(StateContext<States, Events> stateContext) {

    }
}
