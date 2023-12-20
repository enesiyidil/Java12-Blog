package com.enes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginUserRequestDto {

    @Email
    private String email;

    private String password;
}
