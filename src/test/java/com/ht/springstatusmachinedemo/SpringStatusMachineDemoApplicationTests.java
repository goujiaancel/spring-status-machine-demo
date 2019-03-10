package com.ht.springstatusmachinedemo;

import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringStatusMachineDemoApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StateMachineManager stateMachineManager;
	@Autowired
	private StateMachinePersister persist;

	@Test
	public void testPersist() throws Exception {
		StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.PAY);
		persist.persist(stateMachine, "1234");
		logger.info("machineState : {}", stateMachine.getState().getId());
	}

	@Test
	public void testRestore() throws Exception {
		StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
		persist.restore(stateMachine, "1234");
		logger.info("getMachineState : {}", stateMachine.getState().getId());
	}



}
