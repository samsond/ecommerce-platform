package org.example.productservice.controller;

import org.example.productservice.api.resources.ProductResources;
import org.example.productservice.dto.ProductDTO;
import org.example.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController implements ProductResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }
    @Override
    public ResponseEntity<ProductDTO> getProductById(Long id) {
        LOGGER.info("Product information with product id {}", id);
        ProductDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(String categoryName) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }


}
