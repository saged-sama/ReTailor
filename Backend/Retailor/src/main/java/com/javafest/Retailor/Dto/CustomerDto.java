package com.javafest.Retailor.Dto;

import java.time.LocalDateTime;

import com.javafest.Retailor.Entity.Measurements;
import com.javafest.Retailor.Entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String Id;
    private String name;
    private String gender;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String avatar;
    private String address;
    private Measurements measurements;
    private Users users;
}
