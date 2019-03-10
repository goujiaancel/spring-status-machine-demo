package com.ht.springstatusmachinedemo.web;

import com.google.gson.Gson;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.util.SpringUtil;
import com.ht.springstatusmachinedemo.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Collection;

/**
 * @author goujia
 * @version Id: StateMachineController.java, v 0.1 2019/3/8 10:26 goujia Exp $
 */
@RestController
@RequestMapping("/machine")
public class StateMachineController {
    private final Gson gson = new Gson();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StateMachinePersister persist;

    @PostMapping("/start")
    public void doPay(@RequestBody String requestBody, HttpServletRequest request) throws Exception {
        TestVO testVO = gson.fromJson(requestBody, TestVO.class);
        String id = testVO.getId();
        Events events = testVO.getEvents();
        StateMachine<States, Events> stateMachine = SpringUtil.getBean("stateMachine", StateMachine.class);
        logger.info("statusMach ine start...uuid : {}", stateMachine.getUuid());
        stateMachine.start();
        stateMachine.sendEvent(events);
        persist.persist(stateMachine, id);
        logger.info("machineState : {}", stateMachine.getState().getId());
        //stateMachine.sendEvent(Events.CANCEL_ORDER);
        //stateMachine.sendEvent(Events.RECEIVE);
    }

    @PostMapping("/getState")
    public void getMachineState(@RequestBody String requestBody, HttpServletRequest request) throws Exception {
        TestVO testVO = gson.fromJson(requestBody, TestVO.class);
        String id = testVO.getId();
        StateMachine<States, Events> stateMachine = SpringUtil.getBean("stateMachine", StateMachine.class);
        stateMachine = persist.restore(stateMachine, id);
        logger.info("getMachineState : {}", stateMachine.getState().getId());
    }

}
