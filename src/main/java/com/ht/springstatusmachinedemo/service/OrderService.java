package com.ht.springstatusmachinedemo.service;

import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.repository.OrderRepository;
import lombok.extern.java.Log;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.util.Optional;

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
    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(Long id) throws Exception {
        //Order order = orderRepository.getOne(id);
        StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        //this.orderRepository.save(order);
        persister.persist(stateMachine, id.toString());
    }
}
