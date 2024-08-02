package com.example.ordersproject.scheduler;

import com.example.ordersproject.services.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderScheduler {
    private final OrderService orderService;
    @Scheduled(fixedDelayString = "PT1M")
    @SchedulerLock(name ="deleteAllDraftOrders", lockAtLeastForString = "PT1S", lockAtMostForString ="PT3M" )
    public void deleteAllDraftOrders(){
        orderService.deleteAllDraftOrders();
    }
}
