package com.iphayao.demo.product;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper mapper;

    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return productService.findAllProducts()
                .map(mapper::toDto);
    }

    @GetMapping("/{product_id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable("product_id") String productId) {
        return productService.findProductById(new ObjectId(productId))
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{product_id}/price")
    public Mono<ResponseEntity<Double>> getProductPriceById(@PathVariable("product_id") String productId) {
        return productService.findProductPriceById(new ObjectId(productId))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> postProduct(@RequestBody Mono<ProductDto> productDto) {
        return productService.addNewProduct(productDto.map(mapper::toEntity))
                .map(e -> ResponseEntity.created(URI.create("/products/" + e.getId())).body(mapper.toDto(e)));
    }

    @PutMapping("/{product_id}")
    public Mono<ResponseEntity<ProductDto>> putProductById(@PathVariable("product_id") String productId,
                                                        @RequestBody Mono<ProductDto> productDto) {
        return productService.updateProductById(new ObjectId(productId), productDto.map(mapper::toEntity))
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{product_id}")
    public Mono<Void> deleteProductById(@PathVariable("product_id") String productId) {
        return productService.deleteProductById(new ObjectId(productId));
    }

    @DeleteMapping
    public Mono<Void> deleteAllProduct() {
        return productService.deleteAllProduct();
    }
}
