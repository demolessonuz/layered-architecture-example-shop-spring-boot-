package com.shop.service.impl;

import com.shop.model.dto.OrderDTO;
import com.shop.model.entity.Order;
import com.shop.model.entity.OrderItem;
import com.shop.model.entity.Product;
import com.shop.model.mapper.OrderMapper;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);
        
        // Calculate total amount and update stock
        double totalAmount = 0.0;
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + item.getProduct().getId()));
            
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for product: " + product.getName());
            }
            
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice().multiply(java.math.BigDecimal.valueOf(item.getQuantity())));
            totalAmount += item.getSubtotal().doubleValue();
            
            // Update stock
            productService.updateStock(product.getId(), -item.getQuantity());
        }
        
        order.setTotalAmount(java.math.BigDecimal.valueOf(totalAmount));
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO getOrder(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDTOList(orderRepository.findAll());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerEmail(String customerEmail) {
        return orderMapper.toDTOList(orderRepository.findByCustomerEmail(customerEmail));
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
} 