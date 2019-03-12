package com.ht.springstatusmachinedemo.repository;

import com.ht.springstatusmachinedemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
