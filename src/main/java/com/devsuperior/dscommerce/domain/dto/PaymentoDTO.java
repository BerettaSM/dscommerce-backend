package com.devsuperior.dscommerce.domain.dto;

import java.time.Instant;

import com.devsuperior.dscommerce.domain.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentoDTO {

    private Long id;
    private Instant moment;

    public PaymentoDTO(Payment payment) {
        id = payment.getId();
        moment = payment.getMoment();
    }

    public static PaymentoDTO from(Payment payment) {
        return new PaymentoDTO(payment);
    }

}
