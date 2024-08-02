package com.example.ordersproject.services.concrete;

import com.example.ordersproject.dao.entity.OrderEntity;
import com.example.ordersproject.dao.repository.OrderRepository;
import com.example.ordersproject.exception.NotFoundException;
import com.example.ordersproject.model.enums.OrderStatus;
import com.example.ordersproject.model.request.CreateOrUpdateOrderRequest;
import com.example.ordersproject.model.response.OrderResponse;
import com.example.ordersproject.services.abstraction.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.ordersproject.mapper.OrderMapper.ORDER_MAPPER;
import static com.example.ordersproject.model.enums.ExceptionConstants.ORDER_NOT_FOUND;
import static com.example.ordersproject.model.enums.OrderStatus.DELETED;
import static com.example.ordersproject.model.enums.OrderStatus.DRAFT;
import static java.awt.SystemColor.info;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceHandler implements OrderService {
    private final OrderRepository orderRepository;


    @Override
    public void createOrder(CreateOrUpdateOrderRequest request) {
        orderRepository.save(ORDER_MAPPER.builderOrderEntity(request));

    }

    @Override
    public OrderResponse getOrder(Long id) {
        log.info("ActionLog.getOrder.start.success id:{}",id);
        var order = fetchOrderIfExist(id);
        return ORDER_MAPPER.mapEntityToResponse(order);
    }

    @Override
    public List<OrderResponse> getOrders() {
       return orderRepository.findAll()
               .stream()
               .map(ORDER_MAPPER::mapEntityToResponse)
               .toList();


    }

    @Override
    public void deleteOrder(Long id) {
        var order = fetchOrderIfExist(id);
        order.setStatus(DELETED);
        orderRepository.save(order);

    }

    @Override
    public void updateOrderAmount(Long id, CreateOrUpdateOrderRequest request) {
        var order = fetchOrderIfExist(id);
        order.setAmount(request.getAmount());
        orderRepository.save(order);

    }

    @Override
    public void deleteAllDraftOrders() {
        var orders = orderRepository.findByStatus(DRAFT);
        log.info("ActionLog.deleteAllDraftOrders.start");
        orders.forEach(it->it.setStatus(DELETED));
        orderRepository.saveAll(orders);
        log.info("ActionLog.deleteAllDraftOrders.success");
    }
    //@PostConstruct
    //public void test() {
        //var orders = orderRepository.getWithStatus(DELETED);
       // orders.forEach(it -> {
           // System.out.println("ORDER_ID:" + it.getId());
        //});
   // }

    private OrderEntity fetchOrderIfExist(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->{
                    log.error("ActionLog fetchOrderIfExist.error order with id: {} not found",id);

                    return new NotFoundException(ORDER_NOT_FOUND.getMessage(),ORDER_NOT_FOUND.getCode());
                        }
                );
    }
}
