package com.enes.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class PostResponseDto extends PostDto {

    private String userName;

    private String categoryName;
}
