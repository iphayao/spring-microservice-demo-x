package com.iphayao.demo;

import com.iphayao.demo.account.Account;
import com.iphayao.demo.account.AccountDto;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static Mono<AccountDto> mockAccountDtoBody() {
        AccountDto a = AccountDto.builder()
                .username("john.doe")
                .email("john.doe@gmail.com")
                .phone("0888888888")
                .birthday("1999-09-19")
                .build();

        return Mono.just(a);
    }

    public static Account mockAccountEntity() {
        return Account.builder()
                .username("john.doe")
                .email("john.doe@gmail.com")
                .phone("0812345678")
                .birthday(5258L)
                .build();
    }

    public static List<Account> mockAccountEntityList() {
        List<Account> list = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            list.add(mockAccountEntity());
        }

        return list;
    }
}
