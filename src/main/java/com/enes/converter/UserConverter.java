package com.enes.converter;

import com.enes.dto.request.CreateUserRequestDto;
import com.enes.dto.response.LoginUserResponseDto;
import com.enes.dto.response.UserResponseDto;
import com.enes.repository.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class UserConverter implements Converter<User, UserResponseDto> {

    public User createUserRequestDtoToUser(CreateUserRequestDto dto){
        if(dto == null){
            return null;
        }
        return User.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .img(dto.getImg())
                .birthdate(dto.getBirthdate())
                .build();
    }

    @Override
    public UserResponseDto entityToDto(User user){
        if(user == null){
            return null;
        }
        return UserResponseDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .img(user.getImg())
                .registerdate(user.getRegisterdate())
                .build();
    }

    public LoginUserResponseDto userToLoginUserResponseDto(User user) {
        if(user == null){
            return null;
        }
        return LoginUserResponseDto.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .img(user.getImg())
                .birthdate(user.getBirthdate())
                .posts(user.getPosts())
                .build();
    }
}
