package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.entity.Product;
import com.codecool.vizsgaremek.entity.dto.SaveProductDto;
import com.codecool.vizsgaremek.entity.dto.UpdateProductDto;
import com.codecool.vizsgaremek.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public Product saveProduct(@Valid @RequestBody SaveProductDto saveProductDto) {
        return productService.saveProduct(saveProductDto);
    }

    @PutMapping
    public Product updateProduct(@Valid @RequestBody UpdateProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }
}
