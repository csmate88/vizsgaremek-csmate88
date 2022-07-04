package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Product;
import com.codecool.vizsgaremek.entity.dto.SaveProductDto;
import com.codecool.vizsgaremek.entity.dto.UpdateProductDto;
import com.codecool.vizsgaremek.exception.ProductNotFoundException;
import com.codecool.vizsgaremek.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findProductById(long id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(SaveProductDto saveProductDto){
        return productRepository.save(
                Product.builder()
                        .name(saveProductDto.getName())
                        .description(saveProductDto.getDescription())
                        .build());
    }

    public Product updateProduct(UpdateProductDto productDto){
        Product productToUpdate=findProductById(productDto.getId());
        productToUpdate.setName(productDto.getName());
        productToUpdate.setDescription(productDto.getDescription());
       return productRepository.save(productToUpdate);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
