package com.ht.springstatusmachinedemo.entity;

import com.ht.springstatusmachinedemo.enums.Events;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author goujia
 * @version Id: Order.java, v 0.1 2019/3/12 9:38 goujia Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Events events;

}
