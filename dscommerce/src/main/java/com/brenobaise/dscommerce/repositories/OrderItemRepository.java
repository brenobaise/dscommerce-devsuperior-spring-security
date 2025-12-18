package com.brenobaise.dscommerce.repositories;

import com.brenobaise.dscommerce.entities.Order;
import com.brenobaise.dscommerce.entities.OrderItem;
import com.brenobaise.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
