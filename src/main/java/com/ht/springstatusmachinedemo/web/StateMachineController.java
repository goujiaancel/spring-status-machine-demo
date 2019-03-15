package com.ht.springstatusmachinedemo.web;

import com.ht.springstatusmachinedemo.entity.Order;
import com.ht.springstatusmachinedemo.service.OrderService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author goujia
 * @version Id: StateMachineController.java, v 0.1 2019/3/8 10:26 goujia Exp $
 */
@RestController
@RequestMapping("/machine")
@Log
public class StateMachineController {
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
