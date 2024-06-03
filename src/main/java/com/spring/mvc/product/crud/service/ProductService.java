package com.spring.mvc.product.crud.service;

import com.spring.mvc.product.crud.entity.Product;
import com.spring.mvc.product.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        retriveCategoryPath(products);
        return products;
    }

    public List<Product> findAll(Specification<Product> spec, Sort sort) {
        List<Product> products = productRepository.findAll(spec, sort);;
        retriveCategoryPath(products);
        return products;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private void retriveCategoryPath(List<Product> products) {
        for (Product product : products){
            product.getCategory().setPath(categoryService.getCategoryPath(product.getCategory()));
        }
    }

}
