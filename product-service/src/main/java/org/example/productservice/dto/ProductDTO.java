package org.example.productservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public record ProductDTO(String name, String description, Double price,@JsonIgnore Instant lastUpdated, CategoryDTO category) {
    @JsonIgnore
    public boolean isStale() {
        return lastUpdated == null || Instant.now().minusSeconds(60).isAfter(lastUpdated);
    }
}
