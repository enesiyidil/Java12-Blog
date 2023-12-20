package com.enes.dto.response;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryPostResponseDto extends PostDto {

    private String userName;
}
