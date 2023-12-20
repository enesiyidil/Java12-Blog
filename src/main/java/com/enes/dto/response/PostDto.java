package com.enes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder

public class PostDto {

    private String title;

    private String content;

    private LocalDate publishedAt;

    private List<String> userLikes;

}
