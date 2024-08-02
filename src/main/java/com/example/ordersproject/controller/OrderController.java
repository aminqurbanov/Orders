package com.example.ordersproject.controller;


import com.example.ordersproject.model.request.CreateOrUpdateOrderRequest;
import com.example.ordersproject.model.response.OrderResponse;
import com.example.ordersproject.services.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(CREATED)
    public void createOrder(@RequestBody CreateOrUpdateOrderRequest request){
        orderService.createOrder(request);
    }
    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id){
        return orderService.getOrder(id);

    }
    @GetMapping
    public List<OrderResponse> getOrders(){
        return orderService.getOrders();

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public  void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateOrderAmount(@PathVariable Long id,
                                  @RequestBody CreateOrUpdateOrderRequest request){
        orderService.updateOrderAmount(id, request);
    }


}