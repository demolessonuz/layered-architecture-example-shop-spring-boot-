package com.shop.model.mapper;

import com.shop.model.dto.OrderDTO;
import com.shop.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
    List<OrderDTO> toDTOList(List<Order> orders);
    void updateEntity(@MappingTarget Order order, OrderDTO orderDTO);
} 