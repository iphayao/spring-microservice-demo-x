package com.iphayao.demo.account;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Account {
    @Id
    private ObjectId id;
    private String username;
    private String phone;
    private String email;
}
