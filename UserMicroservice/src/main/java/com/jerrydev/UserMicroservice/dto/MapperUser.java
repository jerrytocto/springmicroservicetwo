package com.jerrydev.UserMicroservice.dto;

import com.jerrydev.UserMicroservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MapperUser {

    public UserDTO fromUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public  User fromUserDTO(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        return  user ;
    }

}
