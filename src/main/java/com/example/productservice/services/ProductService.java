package com.example.productservice.services;

import com.example.productservice.dto.CategoryDTO;
import com.example.productservice.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProductService {
    int countProducts();
    Page<ProductDTO> getAllProducts(Pageable pageable);
    ProductDTO getProductByName(String name);
    ProductDTO getProductById(Long id);
    Page<ProductDTO> findByCategory(Pageable pageable, CategoryDTO category);
    void addProduct(ProductDTO productDTO);
    void updateProduct(long id, ProductDTO updatedProductDTO);
    void deleteProduct(long id);
    void moveToTrash(Long id);
    Page<ProductDTO> getInTrash(Pageable pageable);
    List<ProductDTO> getProductsByIds(Set<Long> productIds);
}
