package com.merchant.main.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantRequest {

    @NotNull
    @NotBlank(message = "Merchant name is required")
    private String shop_name;

    @NotNull
    @NotBlank(message = "Owner name is required")
    private String owner_name;

    private String registration_number;

    @NotNull
    @NotBlank(message = "Contact is required")
    private String contact;

    @NotNull
    @NotBlank(message = "Category is required")
    private String category_name;
}
