package com.enes.service;

import com.enes.converter.PostConverter;
import com.enes.dto.request.CreatePostRequestDto;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.PostResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.repository.PostRepository;
import com.enes.repository.entity.Category;
import com.enes.repository.entity.Post;
import com.enes.repository.entity.User;
import com.enes.utility.UtilService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.enes.constant.Messages.LIKED_MESSAGE;
import static com.enes.constant.Messages.LIKED_REMOVE_MESSAGE;

@Service
public class PostService {

    private final PostRepository repository;

    private final PostConverter converter;

    private final UserService userService;

    private final CategoryService categoryService;

    private final @Qualifier("PostUtilService") UtilService<Post, PostResponseDto, PostRepository, PostConverter> utilService;

    public PostService(PostRepository repository,
                       PostConverter converter,
                       UserService userService,
                       CategoryService categoryService,
                       UtilService<Post, PostResponseDto, PostRepository, PostConverter> utilService) {
        this.repository = repository;
        this.converter = converter;
        this.userService = userService;
        this.categoryService = categoryService;
        this.utilService = utilService;
    }

    public List<PostResponseDto> findAll(LocalDate publishedAt) {
        if (publishedAt != null){
            return repository.findAll().stream().map(converter::entityToDto).filter(postResponseDto -> postResponseDto.getPublishedAt().equals(publishedAt)).collect(Collectors.toList());
        }
        return repository.findAll().stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    public PostResponseDto findById(Long postId) {
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()) {
            return converter.entityToDto(optionalPost.get());
        }
        throw new BlogException(Post.class.getName(), ErrorType.NOT_FOUND);
    }

    public boolean createPost(CreatePostRequestDto dto) {
        if (userService.existsByEmail(dto.getUserEmail())) {
            Post post = repository.save(converter.createPostRequestDtoToPost(dto));
            return post.getId() != null;
        }
        throw new BlogException(User.class.getName(), ErrorType.NOT_FOUND);
    }

    public PostResponseDto update(Long postId, PutRequestDto dto) {
        if (Objects.equals(postId, dto.getId())) {
            return utilService.update(dto);
        }
        throw new BlogException("Id", ErrorType.NOT_VALID);
    }

    public boolean delete(Long postId) {
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()) {
            repository.delete(optionalPost.get());
            return repository.findById(postId).isEmpty();
        }
        throw new BlogException(Post.class.getName(), ErrorType.NOT_FOUND);
    }

    public List<PostResponseDto> findAllByUserId(Long userId) {
        return repository.findAllByUser(userService.findByIdUser(userId)).stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    public List<PostResponseDto> findAllByCategoryId(Long categoryId) {
        return repository.findAllByCategory(categoryService.findByIdCategory(categoryId)).stream().map(converter::entityToDto).collect(Collectors.toList());
    }

    public List<PostResponseDto> findAllByCategoryName(String categoryName) {
        Long categoryId = categoryService.findByName(categoryName).getId();
        return findAllByCategoryId(categoryId);
    }

    public List<PostResponseDto> findAllByWord(String search) {
        return findAll(null).stream().filter(postResponseDto ->
            postResponseDto.getTitle().toLowerCase().contains(search) || postResponseDto.getContent().toLowerCase().contains(search)
        ).collect(Collectors.toList());
    }

    public Post findByIdPost(Long postId) {
        Optional<Post> optionalPost = repository.findById(postId);
        if (optionalPost.isPresent()){
            return optionalPost.get();
        }
        throw new BlogException(Post.class.getName(), ErrorType.NOT_FOUND);
    }

    public String likePost(Long postId, Long userId) {
        Post post = findByIdPost(postId);
        User user = userService.findByIdUser(userId);
        if (post.getLikes().contains(user)){
            post.getLikes().remove(user);
            repository.save(post);
            return LIKED_MESSAGE;
        }else {
            post.getLikes().add(user);
            repository.save(post);
            return LIKED_REMOVE_MESSAGE;
        }
    }
}
