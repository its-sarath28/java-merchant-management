package com.merchant.main.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.merchant.main.DTO.CategoryRequest;
import com.merchant.main.Exception.EntityAlreadyExistsException;
import com.merchant.main.Model.Category;
import com.merchant.main.Repository.CategoryRepository;
import com.merchant.main.Response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<Object> createCategory(CategoryRequest category) {

        Optional<Category> existingCategory = categoryRepository
                .findByNameIgnoreCaseAndIgnoreSpaces(category.getCategory_name());

        if (existingCategory.isPresent()) {
            throw new EntityAlreadyExistsException("Category already exists");
        }

        Category newCategory = new Category();
        newCategory.setCategory_name(category.getCategory_name());
        newCategory.setCreated_at(LocalDateTime.now());
        categoryRepository.save(newCategory);

        return ResponseEntity.ok(new SuccessResponse("Category created successfully"));
    }

}
