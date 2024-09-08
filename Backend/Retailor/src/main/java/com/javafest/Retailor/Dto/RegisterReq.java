package com.javafest.Retailor.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq {
    private String email;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String address;
}
