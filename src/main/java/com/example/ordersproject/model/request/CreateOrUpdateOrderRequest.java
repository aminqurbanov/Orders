package com.example.ordersproject.model.request;

import com.example.ordersproject.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateOrderRequest {
    private BigDecimal amount;
}
