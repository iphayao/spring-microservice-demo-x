package com.iphayao.demo.account;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AccountDto {
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String phone;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String birthday;
}
