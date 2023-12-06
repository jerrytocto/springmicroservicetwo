package com.jerrydev.UserMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int id;
    private String brand;
    private String model;
    private int userId;
}
