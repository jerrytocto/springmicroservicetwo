package com.jerrydev.carmcsv.dto;

import com.jerrydev.carmcsv.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDTO fromCar(Car car){
        return CarDTO.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .userId(car.getUserId())
                .build();
    }

    public Car fromCarDTO(CarDTO carDTO){
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setUserId(carDTO.getUserId());
        return car ;
    }
}
