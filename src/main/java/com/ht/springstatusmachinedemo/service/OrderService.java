package com.ht.springstatusmachinedemo.service;

import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.repository.OrderRepository;
import lombok.extern.java.Log;
import net.bytebuddy.asm.Advice;
import org.aspectj.weaver.Constants;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.util.List;
import java.util.Optional;

import static com.ht.springstatusmachinedemo.common.Constants.ORDER_HEADER;

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
    @Autowired
    private PersistStateMachineHandler persistStateMachineHandler;

    public void createOrder(Long id) throws Exception {
        //Order order = orderRepository.getOne(id);
        StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        //this.orderRepository.save(order);
        persister.persist(stateMachine, id.toString());
    }

    public Order findOrderById(Long id) {
        if (!orderRepository.findById(id).isPresent()) {
            return null;
        }
        return orderRepository.findById(id).get();
    }

    public void updateState(Long id,Events events) {
        Order order = this.findOrderById(id);
        if (null == order) {
            return;
        }
        persistStateMachineHandler.handleEventWithState(
                MessageBuilder.withPayload(events.name()).setHeader(ORDER_HEADER, order).build(),
                order.getState().name()
        );
    }

}
