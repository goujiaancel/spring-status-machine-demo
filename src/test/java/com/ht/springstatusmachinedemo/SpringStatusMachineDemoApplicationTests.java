package com.ht.springstatusmachinedemo;

import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringStatusMachineDemoApplicationTests {


	@Test
	public void contextLoads() {
		Thread1 thread1 = new Thread1();
		Thread2 thread2 = new Thread2();
		thread1.start();
		thread2.start();
	}



}

class Thread1 extends Thread{

	@Override
	public void run() {
		StateMachine<States, Events> stateMachine = SpringUtil.getBean("stateMachine",StateMachine.class);
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
}

class Thread2 extends Thread{
	@Override
	public void run() {
		StateMachine<States, Events> stateMachine = SpringUtil.getBean("stateMachine",StateMachine.class);
		stateMachine.start();
		stateMachine.sendEvent(Events.PAY);
		stateMachine.sendEvent(Events.RECEIVE);
	}
}
