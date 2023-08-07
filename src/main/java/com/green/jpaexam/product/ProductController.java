package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductDto;
import com.green.jpaexam.product.model.ProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductRes> postProduct(@RequestBody ProductDto dto) {
        ProductRes res = service.saveProduct(dto);
        return ResponseEntity.ok(res);
    }
}
