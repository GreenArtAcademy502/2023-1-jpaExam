package com.green.jpaexam.product;

import com.green.jpaexam.product.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductRes> postProduct(@RequestBody ProductDto dto) {
        ProductRes res = service.saveProduct2(dto);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<Page<ProductRes>> getProductAll(
            @PageableDefault(sort="number", direction = Sort.Direction.DESC, size=20) Pageable page) {
        return ResponseEntity.ok(service.getProductAll(page));
    }

    @GetMapping("/jpql")
    public ResponseEntity<List<ProductRes>> getProductAllJpql(
            @PageableDefault(sort="number", direction = Sort.Direction.DESC, size=20) Pageable page
            , ProductSelAllParam param) {
        return ResponseEntity.ok(service.getProductAllJpql(page, param));
    }

    @GetMapping("/qdsl")
    public ResponseEntity<List<ProductResQdsl>> getProductAllQdsl(@ParameterObject @PageableDefault(page = 0, sort="number", direction = Sort.Direction.DESC, size=20)
                                                                      Pageable pageable,
                                                                  @RequestParam(required = false) String search) {
        log.info("search : {}", search);
        return ResponseEntity.ok(service.getProductAllQdsl(pageable, search));
    }

    @GetMapping("/{number}")
    public ResponseEntity<ProductRes> getProduct(@PathVariable Long number) {
        return ResponseEntity.ok(service.getProduct(number));
    }

    @PutMapping
    public ResponseEntity<ProductRes> updProduct(@RequestBody ProductUpdDto dto) {
        return ResponseEntity.ok(service.updProduct(dto));
    }

    @DeleteMapping
    public ResponseEntity<Integer> delProduct(@RequestParam Long number) {
        service.delProduct(number);
        return ResponseEntity.ok(1);
    }
}
