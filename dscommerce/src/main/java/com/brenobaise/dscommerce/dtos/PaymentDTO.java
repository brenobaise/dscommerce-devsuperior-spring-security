package com.brenobaise.dscommerce.dtos;

import com.brenobaise.dscommerce.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Payment entity){
        id = entity.getId();
        moment = entity.getMoment();
    }
}
