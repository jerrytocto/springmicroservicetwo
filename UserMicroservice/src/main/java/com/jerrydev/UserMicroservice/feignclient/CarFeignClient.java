package com.jerrydev.UserMicroservice.feignclient;

import com.jerrydev.UserMicroservice.dto.CarDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mcsv-car", url = "http://localhost:8082/api/v1/cars")
public interface CarFeignClient {

    @GetMapping("/user/{userId}")
    List<CarDTO> carsByUserId(@PathVariable int userId);

    @PostMapping()
    CarDTO saveCar(@RequestBody CarDTO carDTO);
}
