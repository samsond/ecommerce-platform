package org.example.productservice.service;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
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

    private final Timer productFetchTimer;

    public ProductService(final ProductRepository productRepository, final ProductMapper mapper, Timer productFetchTimer) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.productFetchTimer = productFetchTimer;
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(mapper::convert)
                .orElseThrow(() -> new ProductNotFoundException("Product with name '" + id + "' not found"));
    }

    public List<ProductDTO> getProductsByCategory(String categoryName) {
        Timer.Sample sample = Timer.start(Metrics.globalRegistry);
        List<Product> products = productRepository.findByCategoryName(categoryName);
        sample.stop(productFetchTimer);
        return products.stream()
                .map(mapper::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByName(String name) {
        Timer.Sample sample = Timer.start(Metrics.globalRegistry);
        List<Product> products = productRepository.findByNameContaining(name);
        sample.stop(productFetchTimer);
        return products.stream()
                .map(mapper::convert)
                .collect(Collectors.toList());
    }


}
