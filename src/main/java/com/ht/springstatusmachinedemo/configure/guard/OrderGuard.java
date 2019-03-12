package com.ht.springstatusmachinedemo.configure.guard;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

/**
 * @author goujia
 * @version Id: OrderGuard.java, v 0.1 2019/3/12 15:19 goujia Exp $
 */
@Component
@Log
public class OrderGuard implements Guard<States,Events> {

    @Override
    public boolean evaluate(StateContext<States, Events> stateContext) {
        log.info("doing guard now stateMachine uuid is "+stateContext.getStateMachine().getUuid().toString()+" , state is "+
                                                                    stateContext.getStateMachine().getState().getId().name());
        return true;
    }
}
