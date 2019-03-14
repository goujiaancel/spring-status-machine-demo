package com.ht.springstatusmachinedemo.configure.listener;

import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.repository.OrderRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import static com.ht.springstatusmachinedemo.common.Constants.ORDER_HEADER;

@Component
@Log
public class OrderPersistChangeListener implements PersistStateMachineHandler.PersistStateChangeListener {
    @Autowired
    private OrderRepository orderRepository;


    /**
     * 状态的变化持久化到DB中
     * @param state
     * @param message
     * @param transition
     * @param stateMachine
     */
    @Override
    public void onPersist(State<String, String> state, Message<String> message, Transition<String, String> transition, StateMachine<String, String> stateMachine) {
        if (null != message && message.getHeaders().containsKey(ORDER_HEADER)) {
            Order order = message.getHeaders().get(ORDER_HEADER, Order.class);
            order.setState(States.valueOf(state.getId()));
            log.info("order state is : " + order);
            orderRepository.save(order);
        }
    }
}
