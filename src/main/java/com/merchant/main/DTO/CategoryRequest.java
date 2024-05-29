package com.merchant.main.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {

    @NotNull
    @NotBlank(message = "Category name is required")
    private String category_name;

    private LocalDateTime created_at;
}
