package com.jerrydev.carmcsv.controller;

import com.jerrydev.carmcsv.dto.CarDTO;
import com.jerrydev.carmcsv.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService ;

    @GetMapping
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable int carId){
        return ResponseEntity.status(HttpStatus.OK).body(carService.getCarById(carId));
    }

    @PostMapping()
    public ResponseEntity<?> saveCar(@RequestBody CarDTO carDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.saveCar(carDTO));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<?> updateCar(@PathVariable int carId, @RequestBody CarDTO carDTO){
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(carDTO,carId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> carsByUserId(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(carService.carsByUserId(userId));
    }

}
