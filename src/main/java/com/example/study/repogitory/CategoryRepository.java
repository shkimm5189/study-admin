package com.example.study.repogitory;

import com.example.study.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long> {
}
