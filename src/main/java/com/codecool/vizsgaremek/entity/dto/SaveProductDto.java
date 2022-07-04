package com.codecool.vizsgaremek.entity.dto;

import com.codecool.vizsgaremek.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveProductDto {
    @Size(min=5)
    private String name;
    @Size(min=2)
    private String description;
    @NotNull
    private Order order;
}
