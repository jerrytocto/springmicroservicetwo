package com.jerrydev.carmcsv.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarDTO {
    private int id;
    private String brand;
    private String model;
    private int userId;
}
