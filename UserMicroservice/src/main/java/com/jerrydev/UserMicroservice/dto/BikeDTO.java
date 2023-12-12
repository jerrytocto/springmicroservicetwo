package com.jerrydev.UserMicroservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BikeDTO {

    private int id;
    private String brand;
    private String model;
    private int userId;
}
