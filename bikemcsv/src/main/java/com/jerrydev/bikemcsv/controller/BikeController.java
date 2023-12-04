package com.jerrydev.bikemcsv.controller;

import com.jerrydev.bikemcsv.dto.BikeDTO;
import com.jerrydev.bikemcsv.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/biks")
public class BikeController {

    @Autowired
    private BikeService bikeService ;

    @GetMapping
    public ResponseEntity<?> getAllBikes(){
        return ResponseEntity.status(HttpStatus.OK).body(bikeService.getAllBikes());
    }

    @GetMapping("/{bikeId}")
    public ResponseEntity<?> getBikeById(@PathVariable int bikeId){
        return ResponseEntity.status(HttpStatus.OK).body(bikeService.getBikeById(bikeId));
    }

    @PostMapping()
    public ResponseEntity<?> saveBike(@RequestBody BikeDTO bikeDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(bikeService.saveBike(bikeDTO));
    }

    @PutMapping("/{bikeId}")
    public ResponseEntity<?> updateBike(@PathVariable int bikeId, @RequestBody BikeDTO bikeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(bikeService.updateBike(bikeDTO,bikeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> bikesByUserId(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(bikeService.bikesByUserId(userId));
    }

}
