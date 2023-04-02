package com.github.youssfbr.apirest.service.interfaces;

import com.github.youssfbr.apirest.entity.OrderHateoas;

import java.util.List;

public interface IOrderService {

    List<OrderHateoas> listAllOrdersHateoas();
    OrderHateoas findOrderById(Long id);
    OrderHateoas newOrder(OrderHateoas newOrder);
    OrderHateoas cancelOrderById(Long id);
    OrderHateoas completeOrderById(Long id);
    void deleteOrder(Long id);

}
