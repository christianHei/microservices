package com.demo.product.controller;

import com.demo.product.entity.Product;
import com.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Product product){
        productRepository.save(product);
    }

    @PutMapping
    public void put(@RequestBody Product product){
        productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Product> product =  productRepository.findById(id);
        product.ifPresent(value -> productRepository.delete(value));
    }

}
