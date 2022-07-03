package com.codecool.vizsgaremek.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveProductDto {
    @Size(min=5)
    private String name;
    @Size(min=2)
    private String description;
    @Positive
    private long inventory;
}
