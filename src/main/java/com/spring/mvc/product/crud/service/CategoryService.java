package com.spring.mvc.product.crud.service;

import com.spring.mvc.product.crud.entity.Category;
import com.spring.mvc.product.crud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public String getCategoryPath(Category category) {
        StringBuilder path = new StringBuilder();
        while (category != null) {
            if (path.length() > 0) {
                path.insert(0, " > ");
            }
            path.insert(0, category.getName());
            category = category.getParent();
        }
        return path.toString();
    }

}
