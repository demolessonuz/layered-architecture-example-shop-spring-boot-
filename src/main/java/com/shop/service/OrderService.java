package com.shop.service;

import com.shop.model.dto.OrderDTO;
import com.shop.model.entity.Order;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrder(Long id);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByCustomerEmail(String customerEmail);
    OrderDTO updateOrderStatus(Long id, Order.OrderStatus status);
    void deleteOrder(Long id);
} 