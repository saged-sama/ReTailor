package com.javafest.Retailor.Dto;


import com.javafest.Retailor.Entity.Measurements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReq {
    private String Id;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private Measurements measurements;
}
