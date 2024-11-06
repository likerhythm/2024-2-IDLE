package com.example.__2_IDLE.order.model;

import com.example.__2_IDLE.global.model.enums.Item;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private Long id;

    private boolean isSameDayDelivery;

    private Customer customer;

    private int completedItemCount;

    private int originalItemCount;

    @Builder.Default
    private List<Item> orderItems = new ArrayList<>();

    public static Order of(Long id, Customer customer, List<Item> orderItems, boolean isSameDayDelivery) {
        return Order.builder()
                .id(id)
                .customer(customer)
                .orderItems(orderItems)
                .isSameDayDelivery(isSameDayDelivery)
                .completedItemCount(0)
                .originalItemCount(orderItems.size())
                .build();
    }
}