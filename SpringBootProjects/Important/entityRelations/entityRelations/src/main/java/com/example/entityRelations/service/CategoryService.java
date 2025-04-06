package com.example.entityRelations.service;

import com.example.entityRelations.exception.IdNotFoundException;
import com.example.entityRelations.model.Category;
import com.example.entityRelations.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) throws IdNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Category with id " + id + " not found"));
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) throws IdNotFoundException {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Category with id " + id + " not found"));
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long categoryId) throws IdNotFoundException {
        if (!categoryRepository.existsById(categoryId)) {
            throw new IdNotFoundException("Category with id " + categoryId + " not found");
        }

        categoryRepository.deleteCategoryReferences(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
