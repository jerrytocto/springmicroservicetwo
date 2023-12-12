package com.jerrydev.UserMicroservice.controller;

import com.jerrydev.UserMicroservice.dto.BikeDTO;
import com.jerrydev.UserMicroservice.dto.CarDTO;
import com.jerrydev.UserMicroservice.dto.UserDTO;
import com.jerrydev.UserMicroservice.entity.User;
import com.jerrydev.UserMicroservice.exeption.DatabaseOperationException;
import com.jerrydev.UserMicroservice.exeption.ResourceNotFoundException;
import com.jerrydev.UserMicroservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
        try{
            return ResponseEntity.ok().body(userService.saveUser(userDTO));
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO){
        try{
            return ResponseEntity.ok().body(userService.updateUser(userDTO,userId));
        }catch (ResourceAccessException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/{userId}/car")
    public ResponseEntity<?> saveCar(@PathVariable int userId, @RequestBody CarDTO carDTO){
        try{
            return ResponseEntity.ok().body(userService.saveCar(userId,carDTO));
        }catch (ResourceAccessException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackCarsByUserId")
    @GetMapping("/{userId}/cars")
    public ResponseEntity<?> carsByUserId(@PathVariable int userId){
        try{
            return ResponseEntity.ok().body(userService.findAllCarsByUserId(userId));
        }catch (ResourceAccessException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackSaveBike")
    @PostMapping("/{userId}/bike")
    public ResponseEntity<?> saveBike(@PathVariable int userId, @RequestBody BikeDTO bikeDTO){
        try{
            return ResponseEntity.ok().body(userService.saveBike(userId,bikeDTO));
        }catch (ResourceAccessException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackBikesByUserId")
    @GetMapping("/{userId}/bikes")
    public ResponseEntity<?> bikesByUserId(@PathVariable int userId){
        try{
            return ResponseEntity.ok().body(userService.findAllBikesByUserId(userId));
        }catch (ResourceAccessException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DatabaseOperationException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    private ResponseEntity<?> fallbackSaveCar(int userId, CarDTO carDTO, RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el carro");
    }

    private ResponseEntity<?> fallbackCarsByUserId(int userId, RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los carros");
    }

    private ResponseEntity<?> fallbackSaveBike(int userId, BikeDTO bikeDTO, RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la moto");
    }

    private ResponseEntity<?> fallbackBikesByUserId(int userId, RuntimeException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener las motos");
    }
}
