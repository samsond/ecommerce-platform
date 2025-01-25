package org.example.productservice.service;

import org.example.productservice.dto.ProductDTO;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.mapper.ProductMapper;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductService(final ProductRepository productRepository, final ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(mapper::convert)
                .orElseThrow(() -> new ProductNotFoundException("Product with name '" + id + "' not found"));
    }

    public List<ProductDTO> getProductsByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return products.stream()
                .map(mapper::convert)
                .collect(Collectors.toList());
    }
}
