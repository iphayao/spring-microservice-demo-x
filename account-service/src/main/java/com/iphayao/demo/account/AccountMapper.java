package com.iphayao.demo.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountDto dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    AccountDto toDto(Account entity);
}
