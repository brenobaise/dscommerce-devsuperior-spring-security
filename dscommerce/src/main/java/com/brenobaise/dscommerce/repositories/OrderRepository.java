package com.brenobaise.dscommerce.repositories;

import com.brenobaise.dscommerce.entities.Order;
import com.brenobaise.dscommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}
