package com.enes.controller;

import com.enes.dto.request.CreatePostRequestDto;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.PostResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.enes.constant.EndPoints.*;
import static com.enes.constant.Messages.*;

@RestController
@RequestMapping(ROOT + POSTS)
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findAll(@RequestParam(required = false) LocalDate publishedAt){
        return ResponseEntity.ok(service.findAll(publishedAt));
    }

    @GetMapping(POST_ID)
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long postId){
        return ResponseEntity.ok(service.findById(postId));
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequestDto dto){
        if (service.createPost(dto)){
            return ResponseEntity.ok(REGISTRATION_SUCCESSFUL_MESSAGE);
        }
        throw new BlogException(ErrorType.REGISTRATION_FAILED);
    }

    @PutMapping(POST_ID)
    public ResponseEntity<PostResponseDto> update(@PathVariable Long postId, @RequestBody PutRequestDto dto){
        return ResponseEntity.ok(service.update(postId, dto));
    }

    @DeleteMapping(POST_ID)
    public ResponseEntity<String> delete(@PathVariable Long postId){
        if (service.delete(postId)){
            return ResponseEntity.ok(DELETION_SUCCESSFUL_MESSAGE);
        }
        throw new BlogException(ErrorType.DELETION_FAILED);
    }

    @GetMapping(USER + USER_ID)
    public ResponseEntity<List<PostResponseDto>> findAllByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @GetMapping(CATEGORY + CATEGORY_ID)
    public ResponseEntity<List<PostResponseDto>> findAllByCategoryId(@PathVariable Long categoryId){
        return ResponseEntity.ok(service.findAllByCategoryId(categoryId));
    }

    @GetMapping(SEARCH + CATEGORY)
    public ResponseEntity<List<PostResponseDto>> findAllByCategoryName(String categoryName){
        return ResponseEntity.ok(service.findAllByCategoryName(categoryName));
    }

    @GetMapping(SEARCH)
    public ResponseEntity<List<PostResponseDto>> findAllByWord(String q){
        return ResponseEntity.ok(service.findAllByWord(q));
    }

    @GetMapping(POST_ID + USER_ID)
    public ResponseEntity<String> likePost(@PathVariable Long postId, @PathVariable Long userId){
        return ResponseEntity.ok(service.likePost(postId, userId));
    }
}
