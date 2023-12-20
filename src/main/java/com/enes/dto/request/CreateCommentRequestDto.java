package com.enes.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateCommentRequestDto {

    private String userEmail;

    private String content;

    private Long postId;
}
