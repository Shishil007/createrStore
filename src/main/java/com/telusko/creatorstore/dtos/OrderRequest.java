package com.telusko.creatorstore.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @Valid
    @NotEmpty(message = "Request must be sent with more than 0 items")
    private List<OrderItemRequest> items;
    @NotBlank(message = "Customer Name is required")
    private String customerName;
    @NotBlank(message = "Customer Email is required")
    @Email(message = "Customer Email is not valid")
    private String customerEmail;

}
