package com.devsuperior.dscommerce.domain.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dscommerce.domain.entities.Order;
import com.devsuperior.dscommerce.domain.enums.OrderStatus;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentoDTO payment;

    @NotEmpty(message = "Must have at least one item")
    private final List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(
            Long id,
            Instant moment,
            OrderStatus status,
            ClientDTO client,
            PaymentoDTO payment,
            List<OrderItemDTO> items) {
        this(id, moment, status, client, payment);
        this.items.addAll(items);
    }

    public OrderDTO(Order order) {
        id = order.getId();
        moment = order.getMoment();
        status = order.getStatus();
        client = ClientDTO.from(order.getClient());
        payment = Optional.ofNullable(order.getPayment())
                .map(PaymentoDTO::from)
                .orElse(null);
        order.getItems()
                .stream()
                .map(OrderItemDTO::from)
                .forEach(items::add);
    }

    public static OrderDTO from(Order order) {
        return new OrderDTO(order);
    }

    public Double getTotal() {
        return items.stream()
            .mapToDouble(OrderItemDTO::getSubTotal)
            .sum();
    }

}
