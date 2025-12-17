package com.brenobaise.dscommerce.repositories;

import com.brenobaise.dscommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
