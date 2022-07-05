package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.entity.Product;
import com.codecool.vizsgaremek.entity.dto.SaveProductDto;
import com.codecool.vizsgaremek.entity.dto.UpdateProductDto;
import com.codecool.vizsgaremek.exception.ProductNotFoundException;
import com.codecool.vizsgaremek.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
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
    public ResponseEntity<Product> findProductById(@PathVariable("id") long id) {
        try{
            return ResponseEntity.ok(productService.findProductById(id));
        } catch(ProductNotFoundException e){
            log.error("PRODUCT NOT FOUND");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody SaveProductDto saveProductDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("NOT VALID PRODUCT");
            bindingResult.getAllErrors().forEach(x->log.error(x.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.saveProduct(saveProductDto));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody UpdateProductDto productDto,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("NOT VALID PRODUCT");
            bindingResult.getAllErrors().forEach(x->log.error(x.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.updateProduct(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        try{
            productService.findProductById(id);
        } catch(ProductNotFoundException e){
            log.error("PRODUCT NOT FOUND");
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
