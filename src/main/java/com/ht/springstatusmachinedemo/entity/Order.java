package com.ht.springstatusmachinedemo.entity;

import com.ht.springstatusmachinedemo.enums.States;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * @author goujia
 * @version Id: Order.java, v 0.1 2019/3/12 9:38 goujia Exp $
 */
@Table(name = "orders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "events")
    @Enumerated(value = EnumType.STRING)
    private States state;

}
