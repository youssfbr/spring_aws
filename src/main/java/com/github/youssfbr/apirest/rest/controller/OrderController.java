package com.github.youssfbr.apirest.rest.controller;

import com.github.youssfbr.apirest.entity.OrderHateoas;
import com.github.youssfbr.apirest.entity.enums.Status;
import com.github.youssfbr.apirest.service.interfaces.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderHateoas>> consultOrderAll() {

        long idOrder;
        Link linkUri;

        List<OrderHateoas> ordersList = orderService.listAllOrdersHateoas();

        if (ordersList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        for (OrderHateoas orderHateoas : ordersList) {
            idOrder = orderHateoas.getId();
            linkUri = linkTo(methodOn(OrderController.class).findOrderById(idOrder)).withSelfRel();
            orderHateoas.add(linkUri);
            linkUri = linkTo(methodOn(OrderController.class).consultOrderAll()).withRel("Costumer order list");
            orderHateoas.add(linkUri);
        }

        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHateoas> findOrderById(@PathVariable Long id) {
        OrderHateoas orderPointer = orderService.findOrderById(id);
        if (orderPointer == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        OrderHateoas order = orderPointer;
        order.add(linkTo(methodOn(OrderController.class).consultOrderAll()).withRel("All orders"));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderHateoas newOrder(@RequestBody OrderHateoas newHateoas) {
        return orderService.newOrder(newHateoas);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Object> cancelOrderById(@PathVariable Long id) {

        OrderHateoas cancelledOrder = orderService.cancelOrderById(id);

        if (cancelledOrder.getStatus() == Status.CANCELLED) {
            cancelledOrder.add(linkTo(methodOn(OrderController.class).cancelOrderById(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderController.class).findOrderById(id)).withRel("Complete order list"));
            return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body("You cant complete the task, the order has a " + cancelledOrder.getStatus() + " status");
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Object> completeOrderById(@PathVariable Long id) {

        OrderHateoas cancelledOrder = orderService.completeOrderById(id);

        if (cancelledOrder.getStatus() == Status.COMPLETED) {
            cancelledOrder.add(linkTo(methodOn(OrderController.class).cancelOrderById(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderController.class).findOrderById(id)).withRel("Complete order list"));
            return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body("You can't complete the task, the order has a " + cancelledOrder.getStatus() + " status");
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

}
