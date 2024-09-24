package com.maju_mundur.MajuMundur.controller;

import com.maju_mundur.MajuMundur.constant.ApiUrl;
import com.maju_mundur.MajuMundur.dto.request.ProductRequest;
import com.maju_mundur.MajuMundur.dto.response.CommonResponse;
import com.maju_mundur.MajuMundur.dto.response.ProductResponse;
import com.maju_mundur.MajuMundur.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.PRODUCT)
public class ProductController {
    private final ProductService productService;

    // Create Product
    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.create(productRequest);
        CommonResponse<ProductResponse> commonResponse = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Product created successfully!")
                .data(productResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    // Get All Products
    @GetMapping
    public ResponseEntity<CommonResponse<List<ProductResponse>>> getAllProducts() {
        List<ProductResponse> productResponses = productService.getAll();
        CommonResponse<List<ProductResponse>> commonResponse = CommonResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Products retrieved successfully!")
                .data(productResponses)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductResponse>> getProductById(@PathVariable String id) {
        ProductResponse productResponse = productService.getProductById(id);
        CommonResponse<ProductResponse> commonResponse = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Product retrieved successfully!")
                .data(productResponse)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ProductResponse>> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct = productService(id, productRequest);
        CommonResponse<ProductResponse> commonResponse = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Product updated successfully!")
                .data(updatedProduct)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    // Delete Product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .message("Product deleted successfully!")
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commonResponse);
    }

    // Update Product Status
    @PutMapping("/{id}/status")
    public ResponseEntity<CommonResponse<String>> updateProductStatusById(@PathVariable String id) {
        productService.updateStatusById(id);
        CommonResponse<String> commonResponse = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Product status updated successfully!")
                .build();
        return ResponseEntity.ok(commonResponse);
    }
}
