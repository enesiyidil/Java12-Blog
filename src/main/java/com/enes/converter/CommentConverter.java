package com.enes.converter;

import com.enes.dto.request.CreateCommentRequestDto;
import com.enes.dto.response.CommentDto;
import com.enes.repository.entity.Comment;
import com.enes.service.PostService;
import com.enes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentConverter {

    private final UserService userService;

    private final PostService postService;

    public CommentDto commentToCommentDto(Comment comment){
        if (comment == null){
            return null;
        }
        return CommentDto.builder()
                .userName(comment.getUser().getFirstname() + " " + comment.getUser().getLastname())
                .content(comment.getContent())
                .publishedAt(comment.getPublishedat())
                .build();
    }

    public Comment createCommentRequestDtoToComment(CreateCommentRequestDto dto) {
        if (dto == null){
            return null;
        }
        return Comment.builder()
                .user(userService.findByEmail(dto.getUserEmail()))
                .content(dto.getContent())
                .post(postService.findByIdPost(dto.getPostId()))
                .build();
    }
}
