package com.merchant.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.merchant.main.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE LOWER(REPLACE(c.category_name, ' ', '')) = LOWER(REPLACE(:category_name, ' ', ''))")
    Optional<Category> findByNameIgnoreCaseAndIgnoreSpaces(@Param("category_name") String category_name);
}
