package com.codecool.vizsgaremek.entity.dto;


import com.codecool.vizsgaremek.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateOrderDto {
    @Digits(integer = Integer.MAX_VALUE,fraction = 0)
    @Positive
    private long id;

    @NotNull
    private long customerId;
    @Size(min=1,message = "You have to order something")
    private List<Product> products;
}
