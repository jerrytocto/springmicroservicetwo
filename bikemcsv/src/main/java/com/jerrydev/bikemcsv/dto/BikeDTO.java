package com.jerrydev.bikemcsv.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BikeDTO {
    private int id;
    private String brand;
    private String model;
    private int userId;
}
