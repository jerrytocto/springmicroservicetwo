package com.jerrydev.UserMicroservice.service;

import com.jerrydev.UserMicroservice.dto.BikeDTO;
import com.jerrydev.UserMicroservice.dto.CarDTO;
import com.jerrydev.UserMicroservice.dto.UserDTO;
import com.jerrydev.UserMicroservice.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUser();

    UserDTO getUserById(int userId);

    UserDTO saveUser (UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, int userId);

    void deleteUser(int userId);

    List<CarDTO> carsByUserId(int userId);

    List<BikeDTO> bikesByUserId(int userId);
}
