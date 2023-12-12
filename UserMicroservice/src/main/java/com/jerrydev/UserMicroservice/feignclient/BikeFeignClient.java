package com.jerrydev.UserMicroservice.feignclient;

import com.jerrydev.UserMicroservice.dto.BikeDTO;
import com.jerrydev.UserMicroservice.dto.CarDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mcsv-Bike",url = "http://localhost:8001/api/v1/biks")
public interface BikeFeignClient {
    @PostMapping()
    BikeDTO saveBike(@RequestBody BikeDTO bikeDTO);

    @GetMapping("/user/{userId}")
    List<BikeDTO> bikesByUserId(@PathVariable int userId);
}
