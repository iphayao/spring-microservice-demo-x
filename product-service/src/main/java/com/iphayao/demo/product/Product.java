package com.iphayao.demo.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private ObjectId id;
    private String sellerId;
    private String name;
    private Double price;
    private String display;
    private String type;
}
