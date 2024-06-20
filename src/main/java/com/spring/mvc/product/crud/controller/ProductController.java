package com.spring.mvc.product.crud.controller;


import com.spring.mvc.product.crud.entity.Product;
import com.spring.mvc.product.crud.service.CategoryService;
import com.spring.mvc.product.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listProducts(Model model,
                               @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField,
                               @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
                               @RequestParam(value = "keyword", required = false) String keyword) {

        Specification<Product> spec = (root, query, cb) -> {
            if (keyword != null && !keyword.isEmpty()) {
                return cb.like(root.get("name"), "%" + keyword + "%");
            }
            return null;
        };

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        List<Product> products = productService.findAll(spec, sort);
        model.addAttribute("products", products);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        return "product-list";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product-form";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        if (id.equals(1L))
            throw new NoSuchElementException();

        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product-form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}
