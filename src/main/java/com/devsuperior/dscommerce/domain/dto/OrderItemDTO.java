package com.devsuperior.dscommerce.domain.dto;

import com.devsuperior.dscommerce.domain.entities.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(OrderItem orderItem) {
        productId = orderItem.getProduct().getId();
        name = orderItem.getProduct().getName();
        price = orderItem.getPrice();
        quantity = orderItem.getQuantity();
    }

    public Double getSubTotal() {
        return price * quantity;
    }

    public static OrderItemDTO from(OrderItem orderItem) {
        return new OrderItemDTO(orderItem);
    }

}
