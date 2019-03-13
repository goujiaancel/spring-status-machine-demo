package com.ht.springstatusmachinedemo.configure.action;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import lombok.extern.java.Log;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * @author goujia
 * @version Id: OrderAction.java, v 0.1 2019/3/12 15:18 goujia Exp $
 */
@Component
@Log
public class OrderAction implements Action<String,String> {

    @Override
    public void execute(StateContext<String, String> stateContext) {
        StateMachine<String, String> stateMachine = stateContext.getStateMachine();
        log.info("doing action now stateMachine uuid is " + stateMachine.getUuid().toString() + " , state is " + stateMachine.getState().getId());

    }
}
