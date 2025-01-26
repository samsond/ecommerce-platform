package org.example.productservice.service;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.dto.ProductDTO;
import org.example.productservice.mapper.ProductMapper;
import org.example.productservice.model.Category;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private ProductService productService;
    private Timer productFetchTimer;
    private ProductMapper productMapper;
    private Timer.Sample sample;
    private MockedStatic<Timer> mockedTimer;

    @BeforeEach
    public void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        productMapper = Mockito.mock(ProductMapper.class);
        productFetchTimer = Timer.builder("product.fetch.latency")
                .description("Time taken to fetch product details")
                .register(Metrics.globalRegistry);
        sample = Mockito.mock(Timer.Sample.class);
        productService = new ProductService(productRepository, productMapper, productFetchTimer);
        mockedTimer = Mockito.mockStatic(Timer.class);
        mockedTimer.when(() -> Timer.start(Metrics.globalRegistry)).thenReturn(sample);
    }

    @AfterEach
    public void tearDown() {
        mockedTimer.close();
    }

    static Stream<List<Product>> productProvider() {
        Category category1 = new Category(1L, "Electronics");
        Category category2 = new Category(2L, "Books");
        return Stream.of(
                Arrays.asList(
                        new Product(1L, "Product1", "Category1", 100.0, category1),
                        new Product(2L, "Product2", "Category1", 200.0, category2)
                ),
                Arrays.asList(
                        new Product(3L, "Product3", "Category2", 300.0, category1),
                        new Product(4L, "Product4", "Category2", 400.0, category2)
                )
        );
    }


    @ParameterizedTest
    @MethodSource("productProvider")
    void testGetProductByCategory(List<Product> products) {
        // Arrange
        when(productRepository.findByCategoryName(anyString())).thenReturn(products);
        for (Product product: products) {
            when(productMapper.convert(product)).thenReturn(new ProductDTO(
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    new CategoryDTO(product.getCategory().getName()))
            );
        }

        // Act
        List<ProductDTO> result = productService.getProductsByCategory(products.get(0).getCategory().getName());
        assertEquals(products.size(), result.size());
        for (int i = 0; i < products.size(); i++) {
            assertEquals(products.get(i).getCategory().getName(), result.get(i).category().name());
        }

        // Verify timer interactions
        verify(sample).stop(productFetchTimer);
    }
}
