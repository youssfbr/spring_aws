package com.github.youssfbr.apirest.service;

import com.github.youssfbr.apirest.entity.OrderHateoas;
import com.github.youssfbr.apirest.entity.enums.Status;
import com.github.youssfbr.apirest.repository.IOrderRepository;
import com.github.youssfbr.apirest.service.exceptions.ResourceNotFoundException;
import com.github.youssfbr.apirest.service.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private static final String NOT_FOUND_MESSAGE = "Ordem não encontrada com ID: ";

    @Override
    @Transactional(readOnly = true)
    public List<OrderHateoas> listAllOrdersHateoas() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderHateoas findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE + id ));
    }

    @Override
    @Transactional
    public OrderHateoas newOrder(OrderHateoas newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    @Transactional
    public OrderHateoas cancelOrderById(Long id) {

        OrderHateoas orderHateoas = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE + id ));

        if (orderHateoas.getStatus() == Status.IN_PROGRESS) {
            orderHateoas.setStatus(Status.CANCELLED);
            orderRepository.save(orderHateoas);
        }

        return orderHateoas;
    }

    @Override
    @Transactional
    public OrderHateoas completeOrderById(Long id) {
        OrderHateoas orderHateoas = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem com ID " + id + " não encontrada."));

        if (orderHateoas.getStatus() == Status.IN_PROGRESS) {
            orderHateoas.setStatus(Status.COMPLETED);
            orderRepository.save(orderHateoas);
        }

        return orderHateoas;
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
