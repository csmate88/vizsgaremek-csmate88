package com.codecool.vizsgaremek.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProductDto {
    @Digits(integer = Integer.MAX_VALUE,fraction = 0)
    @Positive
    private long id;
    @Size(min=5)
    private String name;
    @Size(min=2)
    private String description;
    @Positive
    private long inventory;
}
