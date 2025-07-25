package com.devsuperior.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.domain.dto.OrderDTO;
import com.devsuperior.dscommerce.domain.dto.OrderItemDTO;
import com.devsuperior.dscommerce.domain.entities.Order;
import com.devsuperior.dscommerce.domain.entities.OrderItem;
import com.devsuperior.dscommerce.domain.entities.Product;
import com.devsuperior.dscommerce.domain.entities.User;
import com.devsuperior.dscommerce.domain.enums.OrderStatus;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return orderRepository.findById(id)
                .map(OrderDTO::from)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> findAllByClientId(Long clientId, Pageable pageable) {
        return orderRepository.findAllByClientId(clientId, pageable)
                .map(OrderDTO::from);
    }

    @Transactional
    public OrderDTO save(OrderDTO orderDTO, User user) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(user);
        for (OrderItemDTO orderItemDTO : orderDTO.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());
            OrderItem orderItem = new OrderItem(order, product, orderItemDTO.getQuantity(), product.getPrice());
            order.getItems().add(orderItem);
        }
        Order saved = orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return OrderDTO.from(saved);
    }

}
