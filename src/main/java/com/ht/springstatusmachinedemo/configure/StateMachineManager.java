package com.ht.springstatusmachinedemo.configure;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 * @author goujia
 * @version Id: StatueMachineManager.java, v 0.1 2019/3/8 10:40 goujia Exp $
 */
@Component
public class StateMachineManager implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private StateMachine<States, Events> stateMachine;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        stateMachine = applicationContext.getBean("stateMachine", StateMachine.class);
    }

    public StateMachine<States, Events> getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine<States, Events> stateMachine) {
        this.stateMachine = stateMachine;
    }
}
