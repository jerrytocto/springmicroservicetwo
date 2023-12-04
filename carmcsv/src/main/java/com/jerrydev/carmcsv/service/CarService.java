package com.jerrydev.carmcsv.service;

import com.jerrydev.carmcsv.dto.CarDTO;
import com.jerrydev.carmcsv.entity.Car;

import java.util.List;

public interface CarService {

    List<CarDTO> getAllCars();

    CarDTO getCarById(int carId);

    CarDTO saveCar(CarDTO carDTO);

    CarDTO updateCar(CarDTO carDTO, int carId);

    void deleteCar(int carId);

    List<CarDTO> carsByUserId(int userId);
}
