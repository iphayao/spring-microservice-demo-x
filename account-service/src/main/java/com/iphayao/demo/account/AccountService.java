package com.iphayao.demo.account;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Flux<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    public Mono<Account> findAccountById(ObjectId id) {
        return accountRepository.findById(id);
    }

    public Mono<Account> addNewAccount(Mono<Account> account) {
        return account.flatMap(accountRepository::save);
    }

    public Mono<Account> updateAccountById(ObjectId id, Mono<Account> account) {
        return account.flatMap(a -> accountRepository.findById(id)
                .flatMap(e -> {
                    a.setId(e.getId());
                    return accountRepository.save(a);
                })
        );
    }

    public Mono<Void> deleteAccountById(ObjectId id) {
        return accountRepository.deleteById(id);
    }

}
