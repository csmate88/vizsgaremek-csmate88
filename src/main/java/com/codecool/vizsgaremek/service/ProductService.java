package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Product;
import com.codecool.vizsgaremek.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        Product productToUpdate=findProductById(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setInventory(product.getInventory());
       return productRepository.save(productToUpdate);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
}
