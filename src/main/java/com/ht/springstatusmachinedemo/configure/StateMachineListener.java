package com.ht.springstatusmachinedemo.configure;

import lombok.extern.java.Log;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;


/**
 * @author goujia
 * @version Id: StateMachineListener.java, v 0.1 2019/3/8 16:48 goujia Exp $
 * extends StateMachineListenerAdapter
 */
@Log
@WithStateMachine(name = "stateMachine")
public class StateMachineListener {

    @OnTransition(target = "UNPAID")
    public void create() {
        log.info("订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        log.info("用户完成支付，待收货");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        log.info("用户已收货，订单完成");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "CLOSE")
    public void cancel() {
        log.info("用户取消订单，交易关闭");
    }
}
