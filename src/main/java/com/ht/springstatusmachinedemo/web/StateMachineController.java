package com.ht.springstatusmachinedemo.web;

import com.google.gson.Gson;
import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.vo.TestVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author goujia
 * @version Id: StateMachineController.java, v 0.1 2019/3/8 10:26 goujia Exp $
 */
@RestController
@RequestMapping("/machine")
@Log
public class StateMachineController {
    private final Gson gson = new Gson();
    @Autowired
    private StateMachineManager stateMachineManager;
    @Autowired
    private StateMachinePersister persist;

    @PostMapping("/start")
    public void doPay(@RequestBody String requestBody, HttpServletRequest request) throws Exception {
        StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
        TestVO testVO = gson.fromJson(requestBody, TestVO.class);
        String id = testVO.getId();
        Events events = testVO.getEvents();
        log.info("statusMachine start...uuid is " + stateMachine.getUuid());
        stateMachine.start();
        stateMachine.sendEvent(events);
        persist.persist(stateMachine, id);
        log.info("machineState is " + stateMachine.getState().getId().name());
        //stateMachine.sendEvent(Events.CANCEL_ORDER);
        //stateMachine.sendEvent(Events.RECEIVE);
    }

    @PostMapping("/getState")
    public void getMachineState(@RequestBody String requestBody, HttpServletRequest request) throws Exception {
        TestVO testVO = gson.fromJson(requestBody, TestVO.class);
        String id = testVO.getId();
        StateMachine<States, Events> stateMachine = stateMachineManager.getStateMachine();
        stateMachine = persist.restore(stateMachine, id);
        log.info("getMachineState restore id is " + stateMachine.getState().getId());
    }

}
