package com.jerrydev.bikemcsv.dto;

import com.jerrydev.bikemcsv.entity.Bike;
import org.springframework.stereotype.Component;

@Component
public class BikeMapper {

    public BikeDTO fromBike(Bike bike){
        return BikeDTO.builder()
                .id(bike.getId())
                .brand(bike.getBrand())
                .model(bike.getModel())
                .userId(bike.getUserId())
                .build();
    }

    public Bike fromBikeDTO(BikeDTO bikeDTO){
        Bike bike = new Bike();
        bike.setBrand(bikeDTO.getBrand());
        bike.setModel(bikeDTO.getModel());
        bike.setUserId(bikeDTO.getUserId());
        return bike ;
    }
}
