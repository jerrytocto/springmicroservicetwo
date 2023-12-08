package com.jerrydev.UserMicroservice.feignclient;

import com.jerrydev.UserMicroservice.dto.BikeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mcsv-Bike",url = "http://localhost:8083/api/v1/biks")
public interface BikeFeignClient {

    @PostMapping()
    BikeDTO saveBike(@RequestBody BikeDTO bikeDTO);

    @GetMapping("/user/{userId}")
    BikeDTO bikesByUserId(@PathVariable int userId);
}
