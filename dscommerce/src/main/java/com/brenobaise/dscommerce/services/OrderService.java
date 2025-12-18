package com.brenobaise.dscommerce.services;

import com.brenobaise.dscommerce.dtos.OrderDTO;
import com.brenobaise.dscommerce.dtos.ProductDTO;
import com.brenobaise.dscommerce.entities.Order;
import com.brenobaise.dscommerce.entities.Product;
import com.brenobaise.dscommerce.repositories.OrderRepository;
import com.brenobaise.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order result = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
        return new OrderDTO(result);

    }
}
