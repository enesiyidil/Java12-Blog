package com.enes.dto.response;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPostResponseDto extends PostDto {

    private String categoryName;
}
