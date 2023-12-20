package com.enes.dto.request;

import com.enes.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateUserRequestDto {

    private String firstname;

    private String lastname;

    @Email
    private String email;

    @ValidPassword
    private String password;

    @ValidPassword
    private String rePassword;

    private String img;

    private LocalDate birthdate;
}
