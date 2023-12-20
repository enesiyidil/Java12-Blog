package com.enes.service;

import com.enes.converter.UserConverter;
import com.enes.dto.request.CreateUserRequestDto;
import com.enes.dto.request.LoginUserRequestDto;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.LoginUserResponseDto;
import com.enes.dto.response.UserResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.repository.UserRepository;
import com.enes.repository.entity.User;
import com.enes.utility.UtilService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserConverter converter;

    private final @Qualifier("UserUtilService") UtilService<User, UserResponseDto, UserRepository, UserConverter> utilService;

    public UserService(UserRepository repository,
                       UserConverter converter,
                       UtilService<User, UserResponseDto, UserRepository, UserConverter> utilService) {
        this.repository = repository;
        this.converter = converter;
        this.utilService = utilService;
    }

    public List<UserResponseDto> findAll() {
        return repository.findAll().stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    public UserResponseDto findById(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()) {
            return converter.entityToDto(optionalUser.get());
        } else {
            throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
        }
    }

    public boolean save(CreateUserRequestDto dto) {
        if (existsByEmail(dto.getEmail())) {
            throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
        }
        if (dto.getPassword().equals(dto.getRePassword())) {
            User user = repository.save(converter.createUserRequestDtoToUser(dto));
            return user.getId() != null;
        } else {
            throw new BlogException("Password", ErrorType.NOT_VALID);
        }
    }

    public UserResponseDto update(Long userId, PutRequestDto dto) {
        if (Objects.equals(userId, dto.getId())) {
            return utilService.update(dto);
        }
        throw new BlogException("Id", ErrorType.NOT_VALID);
    }

    public boolean delete(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()) {
            repository.delete(optionalUser.get());
            return repository.findById(userId).isEmpty();
        } else {
            throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
        }
    }

    public User login(String email, String password) {
        Optional<User> optionalUser = repository.findByEmailAndPassword(email, password);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new BlogException(ErrorType.USER_NOT_FOUND_OR_PASSWORD_INCORRECT);
    }

    public boolean existsByEmail(String userEmail) {
        return repository.existsByEmail(userEmail);
    }

    public User findByIdUser(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
        }
    }

    public User findByEmail(String userEmail) {
        Optional<User> optionalUser = repository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
        }
    }
}
