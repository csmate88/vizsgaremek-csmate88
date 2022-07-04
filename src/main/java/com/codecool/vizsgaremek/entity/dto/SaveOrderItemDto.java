package com.codecool.vizsgaremek.entity.dto;

import com.codecool.vizsgaremek.entity.Order;
import com.codecool.vizsgaremek.entity.OrderItem;
import com.codecool.vizsgaremek.entity.OrderItemId;
import com.codecool.vizsgaremek.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveOrderItemDto {

    private Long productId;
    private int quantity;

    public OrderItem convertToEntity(long orderId, Product product, Order order){
        return new OrderItem(new OrderItemId(orderId,productId),product,order,quantity);
    }
}
