package com.enes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreatePostRequestDto {

    private String title;

    private String content;

    private String categoryName;

    private String userEmail;
}
