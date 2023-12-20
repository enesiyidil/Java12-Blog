package com.enes.controller;

import com.enes.dto.request.CreateCommentRequestDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.enes.constant.EndPoints.*;
import static com.enes.constant.Messages.*;

@RestController
@RequestMapping(ROOT + COMMENTS)
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody CreateCommentRequestDto dto){
        if (service.save(dto)){
            return ResponseEntity.ok(REGISTRATION_SUCCESSFUL_MESSAGE);
        }
        throw new BlogException(ErrorType.REGISTRATION_FAILED);
    }

    @DeleteMapping(COMMENT_ID)
    public ResponseEntity<String> delete(@PathVariable Long commentId){
        if (service.delete(commentId)){
            return ResponseEntity.ok(DELETION_SUCCESSFUL_MESSAGE);
        }
        throw new BlogException(ErrorType.DELETION_FAILED);
    }
}
