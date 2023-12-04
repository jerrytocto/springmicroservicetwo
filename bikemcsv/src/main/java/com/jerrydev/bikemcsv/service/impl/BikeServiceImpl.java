package com.jerrydev.bikemcsv.service.impl;

import com.jerrydev.bikemcsv.dto.BikeDTO;
import com.jerrydev.bikemcsv.dto.BikeMapper;
import com.jerrydev.bikemcsv.entity.Bike;
import com.jerrydev.bikemcsv.exeption.ResourceNotFoundException;
import com.jerrydev.bikemcsv.repository.BikeRepository;
import com.jerrydev.bikemcsv.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository bikeRepository ;

    @Autowired
    private BikeMapper bikeMapper ;

    @Override
    public List<BikeDTO> getAllBikes() {

        List<Bike> bikeList = bikeRepository.findAll();
        List<BikeDTO> bikeDTOList = new ArrayList<>() ;

        for(Bike bike: bikeList) bikeDTOList.add(bikeMapper.fromBike(bike));
        return bikeDTOList;
    }

    @Override
    public BikeDTO getBikeById(int bikeId) {
        return bikeMapper.fromBike(bikeRepository.getById(bikeId));
    }

    @Override
    public BikeDTO saveBike(BikeDTO bikeDTO) {

        Bike bike = bikeMapper.fromBikeDTO(bikeDTO);
        return bikeMapper.fromBike(bikeRepository.save(bike));
    }

    @Override
    public BikeDTO updateBike(BikeDTO bikeDTO, int bikeId) {

        Bike bike = bikeRepository.getById(bikeId);
        if(bike==null){
            throw new ResourceNotFoundException("Actualizar bike","id",bikeId);
        }
        bike.setBrand(bikeDTO.getBrand());
        bike.setModel(bikeDTO.getModel());
        bike.setUserId(bikeDTO.getUserId());
        return bikeMapper.fromBike(bikeRepository.save(bike));
    }

    @Override
    public void deleteBike(int bikeId) {
        bikeRepository.deleteById(bikeId);
    }

    @Override
    public List<BikeDTO> bikesByUserId(int userId) {

        List<Bike> bikeList = bikeRepository.findByUserId(userId);
        List<BikeDTO> bikeDTOList = new ArrayList<>() ;

        for(Bike bike: bikeList) bikeDTOList.add(bikeMapper.fromBike(bike));
        return bikeDTOList;
    }

}
