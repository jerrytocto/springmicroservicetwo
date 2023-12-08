package com.jerrydev.UserMicroservice.service.impl;

import com.jerrydev.UserMicroservice.dto.BikeDTO;
import com.jerrydev.UserMicroservice.dto.CarDTO;
import com.jerrydev.UserMicroservice.dto.MapperUser;
import com.jerrydev.UserMicroservice.dto.UserDTO;
import com.jerrydev.UserMicroservice.entity.User;
import com.jerrydev.UserMicroservice.exeption.DatabaseOperationException;
import com.jerrydev.UserMicroservice.exeption.ResourceNotFoundException;
import com.jerrydev.UserMicroservice.feignclient.BikeFeignClient;
import com.jerrydev.UserMicroservice.feignclient.CarFeignClient;
import com.jerrydev.UserMicroservice.repository.UserRepository;
import com.jerrydev.UserMicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperUser mapperUser ;

    @Autowired
    private CarFeignClient carFeignClient;
    @Autowired
    private BikeFeignClient bikeFeignClient;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>( );

        for(User user: userList) userDTOList.add(mapperUser.fromUser(user));
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(int userId) {
        try{
            User userFound=  userRepository.getById(userId);
            if(userFound == null) throw new  ResourceNotFoundException("Buscar curso","id",userId);

            return mapperUser.fromUser(userFound);

        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Buscar usuario", "Error al intentar guardar un curso", ex);
        }
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        try{
            User user = mapperUser.fromUserDTO(userDTO);
            return mapperUser.fromUser(userRepository.save(user));
        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Guardar usuario", "Error al intentar guardar un curso", ex);
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int userId) {
        try{
            User userFound = userRepository.getReferenceById(userId);
            if(userFound == null) throw new  ResourceNotFoundException("Buscar usuario","id",userId);

            userFound.setFirstName(userDTO.getFirstName());
            userFound.setLastName(userDTO.getLastName());
            userFound.setEmail(userDTO.getEmail());
            return mapperUser.fromUser(userRepository.save(userFound));

        }catch (DatabaseOperationException ex){
            throw new DatabaseOperationException("Actualizar usuario", "Error al intentar guardar un curso", ex);
        }
    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public CarDTO saveCar(int userId,CarDTO carDTO) {

        try {
            User userFound = userRepository.getReferenceById(userId);
            if (userFound == null) throw new ResourceNotFoundException("Buscar usuario", "id", userId);

            carDTO.setUserId(userId);
            CarDTO carSave = carFeignClient.saveCar(carDTO);
            return carSave;

        } catch (DatabaseOperationException ex) {
            throw new DatabaseOperationException("Guardar usuario", "Error al intentar guardar un curso", ex);
        }
    }

    @Override
    public BikeDTO saveBike(int userId, BikeDTO bikeDTO) {
        try {
            User userFound = userRepository.getReferenceById(userId);
            if (userFound == null) throw new ResourceNotFoundException("Buscar usuario", "id", userId);

            bikeDTO.setUserId(userId);
            BikeDTO bikeSave = bikeFeignClient.saveBike(bikeDTO);
            return bikeSave;

        } catch (DatabaseOperationException ex) {
            throw new DatabaseOperationException("Guardar usuario", "Error al intentar guardar un curso", ex);
        }
    }
}
