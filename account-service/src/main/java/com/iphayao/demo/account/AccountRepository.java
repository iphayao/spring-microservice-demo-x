package com.iphayao.demo.account;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AccountRepository extends ReactiveMongoRepository<Account, ObjectId> {
}
