package com.brenobaise.dscommerce.services;

import com.brenobaise.dscommerce.dtos.OrderDTO;
import com.brenobaise.dscommerce.dtos.OrderItemDTO;
import com.brenobaise.dscommerce.dtos.ProductDTO;
import com.brenobaise.dscommerce.entities.Order;
import com.brenobaise.dscommerce.entities.OrderItem;
import com.brenobaise.dscommerce.entities.OrderStatus;
import com.brenobaise.dscommerce.entities.Product;
import com.brenobaise.dscommerce.repositories.OrderItemRepository;
import com.brenobaise.dscommerce.repositories.OrderRepository;
import com.brenobaise.dscommerce.repositories.ProductRepository;
import com.brenobaise.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order result = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
        return new OrderDTO(result);

    }

    @Transactional
    public OrderDTO insert( OrderDTO dto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());

        // for every item in the basket
        for(OrderItemDTO itemDTO : dto.getItems()){
            // instantiate a new product and fetch it from the database
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            // create a new orderline, assign the product details and the new order object
            OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity(),product.getPrice());
            // do the backwards associations
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
