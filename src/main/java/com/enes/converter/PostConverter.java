package com.enes.converter;

import com.enes.dto.request.CreatePostRequestDto;
import com.enes.dto.response.PostResponseDto;
import com.enes.repository.entity.Post;
import com.enes.service.CategoryService;
import com.enes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostConverter implements Converter<Post, PostResponseDto>{

    private final CategoryService categoryService;

    private final UserService userService;

    @Override
    public PostResponseDto entityToDto(Post post){
        if (post == null){
            return null;
        }
        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .publishedAt(post.getPublishedat())
                .userName(post.getUser().getFirstname() + " " + post.getUser().getLastname())
                .categoryName(post.getCategory().getName())
                .userLikes(post.getLikes().stream().map(user -> user.getFirstname() + " " + user.getLastname()).collect(Collectors.toList()))
                .build();
    }

    public Post createPostRequestDtoToPost(CreatePostRequestDto dto) {
        if (dto == null){
            return null;
        }
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(categoryService.createOrSave(dto.getCategoryName()))
                .user(userService.findByEmail(dto.getUserEmail()))
                .build();
    }
}
