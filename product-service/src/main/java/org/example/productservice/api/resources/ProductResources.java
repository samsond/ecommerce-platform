package org.example.productservice.api.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.productservice.dto.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Products", description = "Operations related to product management")
public interface ProductResources {

    @Operation(
            summary = "Fetch product details by ID",
            description = "Retrieve detailed information of a product using its unique ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            description = "Unique identifier of the product",
                            required = true,
                            example = "101"
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product details fetched successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> getProductById(@PathVariable Long id);

    @Operation(
            summary = "Fetch products by category name",
            description = "Retrieve a list of products filtered by category name",
            parameters = {
                    @Parameter(
                            name = "categoryName",
                            in = ParameterIn.PATH,
                            description = "The name of the category to filter products by",
                            required = true,
                            example = "Electronics"
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of products fetched successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No products found for the given category"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping(value = "/products/categories/{categoryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String categoryName);

    @Operation(
            summary = "Fetch products by name",
            description = "Retrieve a list of products filtered by name",
            parameters = {
                    @Parameter(
                            name = "name",
                            in = ParameterIn.PATH,
                            description = "The name of the product to filter by",
                            required = true,
                            example = "Laptop"
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of products fetched successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No products found for the given name"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping(value = "/products/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String name);

}
