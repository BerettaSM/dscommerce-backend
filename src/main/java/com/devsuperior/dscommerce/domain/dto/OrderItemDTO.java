package com.devsuperior.dscommerce.domain.dto;

import com.devsuperior.dscommerce.domain.entities.OrderItem;

import lombok.Getter;

@Getter
public class OrderItemDTO {

    private final Long productId;
    private final String name;
    private final Double price;
    private final Integer quantity;

    public OrderItemDTO(Long productId, String name, Double price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

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
