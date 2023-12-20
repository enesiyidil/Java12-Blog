package com.enes.service;

import com.enes.converter.CommentConverter;
import com.enes.dto.request.CreateCommentRequestDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.repository.CommentRepository;
import com.enes.repository.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    private final CommentConverter converter;

    public boolean save(CreateCommentRequestDto dto) {
        Comment comment = repository.save(converter.createCommentRequestDtoToComment(dto));
        return comment.getId() != null;
    }

    public boolean delete(Long commentId) {
        Optional<Comment> optionalcommentId = repository.findById(commentId);
        if (optionalcommentId.isPresent()) {
            repository.delete(optionalcommentId.get());
            return repository.findById(commentId).isEmpty();
        }
        throw new BlogException(Comment.class.getName(), ErrorType.NOT_FOUND);
    }
}
