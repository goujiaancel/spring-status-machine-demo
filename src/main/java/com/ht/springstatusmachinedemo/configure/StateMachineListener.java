package com.ht.springstatusmachinedemo.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;


/**
 * @author goujia
 * @version Id: StateMachineListener.java, v 0.1 2019/3/8 16:48 goujia Exp $
 */
@WithStateMachine(name = "stateMachine")
public class StateMachineListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        logger.info("用户完成支付，待收货");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        logger.info("用户已收货，订单完成");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "CLOSE")
    public void cancel() {
        logger.info("用户取消订单，交易关闭");
    }
}
