package com.jerrydev.UserMicroservice.service.impl;

import com.jerrydev.UserMicroservice.dto.BikeDTO;
import com.jerrydev.UserMicroservice.dto.CarDTO;
import com.jerrydev.UserMicroservice.dto.MapperUser;
import com.jerrydev.UserMicroservice.dto.UserDTO;
import com.jerrydev.UserMicroservice.entity.User;
import com.jerrydev.UserMicroservice.exeption.DatabaseOperationException;
import com.jerrydev.UserMicroservice.exeption.ResourceNotFoundException;
import com.jerrydev.UserMicroservice.repository.UserRepository;
import com.jerrydev.UserMicroservice.restemplateconfig.UserRestTemplate;
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
    private UserRestTemplate userRestTemplate;

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
    public List<CarDTO> carsByUserId(int userId) {

        List<CarDTO> carDTOList = userRestTemplate.restTemplate().getForObject(
                "http://localhost:8082/api/v1/cars/user/"+userId, List.class);
        return carDTOList;
    }

    @Override
    public List<BikeDTO> bikesByUserId(int userId) {

        List<BikeDTO> bikeDTOList = userRestTemplate.restTemplate().getForObject(
                "http://localhost:8083/api/v1/biks/user/"+userId, List.class);

        return bikeDTOList;
    }
}
