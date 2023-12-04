package com.jerrydev.bikemcsv.service;

import com.jerrydev.bikemcsv.dto.BikeDTO;

import java.util.List;

public interface BikeService {

    List<BikeDTO> getAllBikes();

    BikeDTO getBikeById(int carId);

    BikeDTO saveBike(BikeDTO bikeDTO);

    BikeDTO updateBike(BikeDTO bikeDTO, int bikeId);

    void deleteBike(int bikeId);

    List<BikeDTO> bikesByUserId(int userId);
}
