package com.example.entityRelations.repository;

import com.example.entityRelations.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM book_category WHERE category_id = :categoryId", nativeQuery = true)
    void deleteCategoryReferences(Long categoryId);

}
