package com.enes.controller;

import com.enes.dto.request.CreateUserRequestDto;
import com.enes.dto.request.LoginUserRequestDto;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.LoginUserResponseDto;
import com.enes.dto.response.UserResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.repository.entity.User;
import com.enes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static com.enes.constant.EndPoints.*;
import static com.enes.constant.Messages.*;

@RestController
@RequestMapping(ROOT + USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(USER_ID)
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long userId){
        return ResponseEntity.ok(service.findById(userId));
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Validated CreateUserRequestDto dto){
        if(service.save(dto)){
            return ResponseEntity.ok(REGISTRATION_SUCCESSFUL_MESSAGE);
        }else {
            throw new BlogException(ErrorType.REGISTRATION_FAILED);
        }
    }

    @PutMapping(USER_ID)
    public ResponseEntity<UserResponseDto> update(@PathVariable Long userId, @RequestBody PutRequestDto dto){
        return ResponseEntity.ok(service.update(userId, dto));
    }

    @DeleteMapping(USER_ID)
    public ResponseEntity<String> delete(@PathVariable Long userId){
        if(service.delete(userId)){
            return ResponseEntity.ok(DELETION_SUCCESSFUL_MESSAGE);
        }else {
            throw new BlogException(ErrorType.DELETION_FAILED);
        }
    }

    @PostMapping(LOGIN)
    public RedirectView login(@Validated @RequestParam String email, @RequestParam String password){
        Long id = service.login(email, password).getId();
        if (id != null){
            return new RedirectView("/homepage/" + id);
        }
        throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
    }
}
