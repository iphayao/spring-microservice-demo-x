package com.iphayao.demo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String sellerId;
    private String name;
    private Double price;
    private String display;
    private String type;
}
