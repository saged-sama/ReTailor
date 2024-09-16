package com.javafest.Retailor.Dto;


import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq {
    private String email;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
    @Nullable
    private String gender;
    private String phone;
    @Nullable
    private String address;
}
