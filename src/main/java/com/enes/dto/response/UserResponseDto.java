package com.enes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {

    private String firstname;

    private String lastname;

    private String email;

    private String img;

    private LocalDate birthdate;

    private LocalDate registerdate;
}
