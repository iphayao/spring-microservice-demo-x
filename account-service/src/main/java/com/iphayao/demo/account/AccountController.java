package com.iphayao.demo.account;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper mapper;

    @GetMapping
    public Flux<AccountDto> getAllAccount() {
        return accountService.findAllAccount()
                .map(mapper::toDto);
    }

    @PostMapping
    public Mono<ResponseEntity<AccountDto>> postAccount(@RequestBody AccountDto account) {
        return accountService.addNewAccount(mapper.toEntity(account))
                .map(e -> ResponseEntity.ok(mapper.toDto(e)));
    }

    @GetMapping("/{user_id}")
    public Mono<ResponseEntity<AccountDto>> getAccountById(@PathVariable("user_id") String userId) {
        return accountService.findAccountById(new ObjectId(userId))
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{user_id}")
    public Mono<ResponseEntity<AccountDto>> putAccountById(@PathVariable("user_id") String userId,
                                           @RequestBody AccountDto account) {
        return accountService.updateAccountById(new ObjectId(userId), mapper.toEntity(account))
                .map(e -> ResponseEntity.ok(mapper.toDto(e)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{user_id}")
    public Mono<Void> deleteAccountById(@PathVariable("user_id") String userId) {
        return accountService.deleteAccountById(new ObjectId(userId));
    }
}
