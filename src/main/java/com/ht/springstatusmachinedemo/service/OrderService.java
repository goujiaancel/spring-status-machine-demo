package com.ht.springstatusmachinedemo.service;

import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * @author goujia
 * @version Id: OrderService.java, v 0.1 2019/3/12 11:21 goujia Exp $
 */
@Service
@Log
public class OrderService {
    @Autowired
    StateMachineManager stateMachineManager;
    @Autowired
    private StateMachinePersister persister;

    public void createOrder(Order order) throws Exception {
        StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
        stateMachine.start();
        stateMachine.sendEvent(order.getEvents());
        persister.persist(stateMachine, order.getId());
    }
}
