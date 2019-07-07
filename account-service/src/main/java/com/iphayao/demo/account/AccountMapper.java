package com.iphayao.demo.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthday", expression = "java(LocalDate.parse(dto.getBirthday()).toEpochDay())")
    Account toEntity(AccountDto dto);

    @Mapping(target = "id", expression = "java(entity.getId().toString())")
    @Mapping(target = "birthday", expression = "java(LocalDate.ofEpochDay(entity.getBirthday()).toString())")
    AccountDto toDto(Account entity);
}
