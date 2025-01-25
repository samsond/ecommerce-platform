package org.example.productservice.dto;

public record ProductDTO(String name, String description, Double price, CategoryDTO category) {}
