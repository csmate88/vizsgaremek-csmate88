package com.codecool.vizsgaremek.entity.dto;

import com.codecool.vizsgaremek.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCustomerDto {
    @Digits(integer = Integer.MAX_VALUE,fraction = 0)
    @Positive
    private long id;

    @NotNull
    private String name;

    @Schema(description = "Email address, must be unique", example = "something@gmail.com")
    @Email
    private String email;

    @Schema(description = "Telephone number", example = "+36-xx-xxxxxxx")
    @Pattern(regexp = "\\+36-\\d{2}-\\d{6,7}",message = "Phone number format ex: +36-xx-xxxxxxx")
    private String telephoneNumber;

    @Size(min=10)
    private String address;
    private List<Order> orders;
}
