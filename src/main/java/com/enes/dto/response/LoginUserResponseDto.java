package com.enes.dto.response;

import com.enes.repository.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginUserResponseDto {

    private String firstname;

    private String lastname;

    @Email
    private String email;

    private String img;

    private LocalDate birthdate;

    private List<Post> posts;
}
