package com.ht.springstatusmachinedemo.web;

import com.google.gson.Gson;
import com.ht.springstatusmachinedemo.configure.StateMachineManager;
import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.enums.Events;
import com.ht.springstatusmachinedemo.enums.States;
import com.ht.springstatusmachinedemo.service.OrderService;
import com.ht.springstatusmachinedemo.vo.TestVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Autowired
    private OrderService orderService;

    @GetMapping("/getAll")
    public List<Order> getAll() throws Exception {
        return orderService.findAll();
    }

    @GetMapping("/order")
    public Order getOrder(@RequestParam("id") Long id ) throws Exception {
        return orderService.findOrderById(id);
    }

}
