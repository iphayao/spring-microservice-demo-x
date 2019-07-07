package com.iphayao.demo.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
