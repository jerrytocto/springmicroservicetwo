package com.jerrydev.carmcsv.service.impl;

import com.jerrydev.carmcsv.dto.CarDTO;
import com.jerrydev.carmcsv.dto.CarMapper;
import com.jerrydev.carmcsv.entity.Car;
import com.jerrydev.carmcsv.exeption.ResourceNotFoundException;
import com.jerrydev.carmcsv.repository.CarRepository;
import com.jerrydev.carmcsv.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository ;

    @Autowired
    private CarMapper carMapper ;

    @Override
    public List<CarDTO> getAllCars() {

        List<Car> carList = carRepository.findAll();
        List<CarDTO> carDTOList = new ArrayList<>() ;

        for(Car car: carList) carDTOList.add(carMapper.fromCar(car));
        return carDTOList;
    }

    @Override
    public CarDTO getCarById(int carId) {
        return carMapper.fromCar(carRepository.getById(carId));
    }

    @Override
    public CarDTO saveCar(CarDTO carDTO) {

        Car car = carMapper.fromCarDTO(carDTO);
        return carMapper.fromCar(carRepository.save(car));
    }

    @Override
    public CarDTO updateCar(CarDTO carDTO, int carId) {

        Car car = carRepository.getById(carId);
        if(car==null){
            throw new ResourceNotFoundException("Actualizar car","id",carId);
        }
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setUserId(carDTO.getUserId());
        return carMapper.fromCar(carRepository.save(car));
    }

    @Override
    public void deleteCar(int carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public List<CarDTO> carsByUserId(int userId) {

        List<Car> carList = carRepository.findByUserId(userId);
        List<CarDTO> carDTOList = new ArrayList<>() ;

        for(Car car: carList) carDTOList.add(carMapper.fromCar(car));
        return carDTOList;
    }
}
