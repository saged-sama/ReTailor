package com.javafest.Retailor.Dto;

import java.time.LocalDateTime;

import com.javafest.Retailor.Entity.Users;
import com.javafest.Retailor.Enum.TailorStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TailorDto {
    private String id;
    private String name;
    private String bio;
    private String skills;
    private String nationalId;
    private String tradeLicense;
    private LocalDateTime submissionDate;
    private LocalDateTime approvalDate;
    private String location;
    private Users users;
    private TailorStatus tailorStatus;
}
