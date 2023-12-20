package com.enes.utility;

import com.enes.converter.CategoryConverter;
import com.enes.converter.Converter;
import com.enes.converter.PostConverter;
import com.enes.converter.UserConverter;
import com.enes.dto.request.PutRequestDto;
import com.enes.dto.response.CategoryResponseDto;
import com.enes.dto.response.PostResponseDto;
import com.enes.dto.response.UserResponseDto;
import com.enes.exception.BlogException;
import com.enes.exception.ErrorType;
import com.enes.repository.CategoryRepository;
import com.enes.repository.PostRepository;
import com.enes.repository.UserRepository;
import com.enes.repository.entity.Category;
import com.enes.repository.entity.Post;
import com.enes.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;


public class UtilService<ENTITY, DTO, R extends JpaRepository<ENTITY, Long>, C extends Converter<ENTITY, DTO>> {

    private final R repository;

    private final C converter;

    private final Class<ENTITY> clazz;

    public UtilService(R repository, C converter, Class<ENTITY> clazz) {
        this.repository = repository;
        this.converter = converter;
        this.clazz = clazz;
    }

    public DTO update(PutRequestDto dto) {
        Optional<ENTITY> optionalEntity = repository.findById(dto.getId());
        if (optionalEntity.isPresent()) {
            ENTITY entity = optionalEntity.get();
            Arrays.stream(entity.getClass().getDeclaredMethods()).filter(method -> method.getName().contains("set")).forEach(method -> {
                String fieldName = method.getName().replace("set", "").toLowerCase(Locale.ENGLISH);
                if (dto.getFields().containsKey(fieldName)) {
                    try {
                        method.invoke(entity, dto.getFields().get(fieldName));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new BlogException(ErrorType.UNEXPECTED_ERROR);
                    }
                }
            });
            return converter.entityToDto(repository.save(entity));
        } else {
            throw new BlogException(clazz.getName(), ErrorType.NOT_FOUND);
        }
    }

}

@Service("CategoryUtilService")
class CategoryUtilService extends UtilService<Category, CategoryResponseDto, CategoryRepository, CategoryConverter> {

    public CategoryUtilService(CategoryRepository repository, CategoryConverter converter, @Qualifier("category") Class<Category> clazz) {
        super(repository, converter, clazz);
    }
}

@Service("PostUtilService")
class PostUtilService extends UtilService<Post, PostResponseDto, PostRepository, PostConverter> {

    public PostUtilService(PostRepository repository, PostConverter converter, @Qualifier("post") Class<Post> clazz) {
        super(repository, converter, clazz);
    }
}

@Service("UserUtilService")
class UserUtilService extends UtilService<User, UserResponseDto, UserRepository, UserConverter> {

    public UserUtilService(UserRepository repository, UserConverter converter, @Qualifier("user") Class<User> clazz) {
        super(repository, converter, clazz);
    }
}
