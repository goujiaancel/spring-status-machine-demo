package com.ht.springstatusmachinedemo;

import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.service.OrderService;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class SpringStatusMachineDemoApplicationTests {
	@Autowired
	private OrderService orderService;
	@Autowired
	StateMachineManager stateMachineManager;
	@Autowired
	private StateMachinePersister persister;

	@Test
	public void testInit() {
		StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
		stateMachine.start();
		log.info("stateMachine current state is :" + stateMachine.getState().getId().name());
	}

	@Test
	public void testSentEvent() throws Exception {
		StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		//获取当前状态
		log.info("stateMachine current state is : "+stateMachine.getState().getId().name());
	}

	@Test
	public void testPersist() throws Exception {
		StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		//当前状态机信息
		log.info("stateMachine current state is :" + stateMachine.getState().getId().name());
		persister.persist(stateMachine, "1234567890");
	}

	@Test
	public void testRestore() throws Exception {
		StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
		persister.restore(stateMachine, "1234567890");
		//当前状态机信息
		log.info("stateMachine current state is :" + stateMachine.getState().getId().name());
	}

	@Test
	public void testService() throws Exception {
		this.orderService.createOrder(12122121212121L);
	}

	@Test
	public void testServiceUpdateState(){
		orderService.updateState(22L,Events.CANCEL_ORDER);
	}

}
