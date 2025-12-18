package com.brenobaise.dscommerce.dtos;

import com.brenobaise.dscommerce.entities.Order;
import com.brenobaise.dscommerce.entities.OrderItem;
import com.brenobaise.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Must have at least one item")
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment =  (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());

        // for every item inside the entity itemm,
        // convert each item to an order item dto and save it to the dto list
        for(OrderItem item : entity.getItems()){
            items.add(new OrderItemDTO(item));
        }
    }
    public Double getTotal(){
        double sum = 0;
        for(OrderItemDTO item : items){
            sum += item.getSubTotal();
        }
        return sum;
    }
}
