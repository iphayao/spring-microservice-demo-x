package com.iphayao.demo.product;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> findProductById(ObjectId id) {
        return productRepository.findById(id);
    }

    public Mono<Double> findProductPriceById(ObjectId id) {
        return productRepository.findById(id).flatMap(p -> Mono.just(p.getPrice()));
    }

    public Mono<Product> addNewProduct(Mono<Product> product) {
        return product.flatMap(p -> productRepository.save(p));
    }

    public Mono<Product> updateProductById(ObjectId id, Mono<Product> product) {
        return product.flatMap(p -> productRepository.findById(id)
                .flatMap(e -> {
                    p.setId(e.getId());
                    return productRepository.save(p);
                })
        );
    }

    public Mono<Void> deleteProductById(ObjectId id) {
        return productRepository.deleteById(id);
    }

    public Mono<Void> deleteAllProduct() {
        return productRepository.deleteAll();
    }
}
